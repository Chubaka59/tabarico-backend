package com.gtarp.tabaricobackend.services.impl;

import com.gtarp.tabaricobackend.dto.accounting.*;
import com.gtarp.tabaricobackend.entities.User;
import com.gtarp.tabaricobackend.entities.accounting.AccountingRebootInformation;
import com.gtarp.tabaricobackend.entities.accounting.CustomerSale;
import com.gtarp.tabaricobackend.entities.accounting.ExporterSale;
import com.gtarp.tabaricobackend.entities.accounting.TypeOfSale;
import com.gtarp.tabaricobackend.repositories.UserRepository;
import com.gtarp.tabaricobackend.repositories.accounting.AccountingRebootInformationRepository;
import com.gtarp.tabaricobackend.repositories.accounting.CustomerSaleRepository;
import com.gtarp.tabaricobackend.repositories.accounting.ExporterSaleRepository;
import com.gtarp.tabaricobackend.repositories.accounting.RewardRepository;
import com.gtarp.tabaricobackend.services.CustomerSaleService;
import com.gtarp.tabaricobackend.services.DashboardService;
import com.gtarp.tabaricobackend.services.ExporterSaleService;
import com.gtarp.tabaricobackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerSaleService customerSaleService;
    @Autowired
    private ExporterSaleService exporterSaleService;
    @Autowired
    private CustomerSaleRepository customerSaleRepository;
    @Autowired
    private ExporterSaleRepository exporterSaleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountingRebootInformationRepository accountingRebootInformationRepository;
    @Autowired
    private RewardRepository rewardRepository;

    public PersonalDashboardDto getPersonalDashboardDto(String username) {
        User user = userService.getByUsername(username);
        List<CustomerSaleDto> customerSaleDtoList = customerSaleService.findAllByUserForCurrentWeek(username);
        List<ExporterSaleDto> exporterSaleDtoList = exporterSaleService.findAllByUserForCurrentWeek(username);

        LocalDateTime lastRebootDate = accountingRebootInformationRepository.findAll().getFirst().getAccountingRebootDate();
        List<TopSellerDto> topSellerDtoList = getTopSellers(lastRebootDate);

        return new PersonalDashboardDto(user, customerSaleDtoList, exporterSaleDtoList, topSellerDtoList);
    }

    @Override
    public List<DashboardDto> getDashboardDto() {
        LocalDateTime lastRebootDate = accountingRebootInformationRepository.findAll().getFirst().getAccountingRebootDate();

        List<DashboardDto> dashboardDtoList = new ArrayList<>();

        List<User> userList = userService.getAll();
        for (User user : userList) {
            DashboardDto dashboardDto = new DashboardDto();
            dashboardDto.setRoleName(user.getRole().getName());
            dashboardDto.setUsername(user.getUsername());

            //On recupere toutes les ventes client pour le user et on groupe par type de vente
            List<CustomerSale> customerSaleList = customerSaleRepository.findAllByUserAndDateAfter(user, lastRebootDate);
            Map<TypeOfSale, Integer> salesByType = getCustomerSalesByTypeOfSales(customerSaleList);
            dashboardDto.setCleanMoneyCustomerSales(salesByType.get(TypeOfSale.cleanMoney) != null ? salesByType.get(TypeOfSale.cleanMoney) : 0);
            dashboardDto.setDirtyMoneyCustomerSales(salesByType.get(TypeOfSale.dirtyMoney) != null ? salesByType.get(TypeOfSale.dirtyMoney) : 0);

            //On fait la somme de toutes les ventes exportateurs
            List<ExporterSale> exporterSaleList = exporterSaleRepository.findAllByUserAndDateAfter(user, lastRebootDate);
            dashboardDto.setEnterpriseAmountExporterSales(getExporterSalesMoney(exporterSaleList));
            dashboardDto.setQuantityExporterSales(getExporterSalesQuantity(exporterSaleList));

            dashboardDto.setQuota(user.isQuota());
            dashboardDto.setExporterQuota(user.isExporterQuota());
            if (user.isQuota() && user.isExporterQuota()) {
                dashboardDto.setCleanMoneySalary(user.getCleanMoneySalary());
                dashboardDto.setDirtyMoneySalary(user.getDirtyMoneySalary());
            } else {
                dashboardDto.setCleanMoneySalary(0);
                dashboardDto.setDirtyMoneySalary(0);
            }
            dashboardDto.setCleanMoneySalaryPreviousWeek(user.getCleanMoneySalaryPreviousWeek());
            dashboardDto.setDirtyMoneySalaryPreviousWeek(user.getDirtyMoneySalaryPreviousWeek());
            if (user.getEndOfHoliday() != null && user.getEndOfHoliday().isAfter(LocalDate.now())) {
                dashboardDto.setHoliday(user.isHoliday());
                dashboardDto.setEndOfHoliday(user.getEndOfHoliday());
            } else {
                dashboardDto.setHoliday(false);
                dashboardDto.setEndOfHoliday(null);
                user.setHoliday(false);
                user.setEndOfHoliday(null);
                userRepository.save(user);
            }

            dashboardDto.setWarning1(user.isWarning1());
            dashboardDto.setWarning2(user.isWarning2());

            dashboardDtoList.add(dashboardDto);
        }
        return dashboardDtoList;
    }

    @Override
    public void updateUser(String username, DashboardDto dashboardDto) {
        User user = userService.getByUsername(username);
        boolean before = user.isQuota() && user.isExporterQuota();
        boolean after = dashboardDto.isQuota() && dashboardDto.isExporterQuota();
        user.updateFromDashboard(dashboardDto);
        if (!before && after) {
            user.setCleanMoneySalary(user.getCleanMoneySalary() + user.getRole().getSalary());
        } else if(before && !after) {
            user.setCleanMoneySalary(user.getCleanMoneySalary() - user.getRole().getSalary());
        }
        userRepository.save(user);
    }

    @Override
    public void setSalesBlocked(boolean blocked) {
        AccountingRebootInformation accountingRebootInformation = accountingRebootInformationRepository.findAll().getFirst();
        accountingRebootInformation.setSalesLocked(blocked);
        accountingRebootInformationRepository.save(accountingRebootInformation);
    }

    @Override
    public boolean getSalesBlocked() {
        return accountingRebootInformationRepository.findAll().getFirst().isSalesLocked();
    }

    private static Map<TypeOfSale, Integer> getCustomerSalesByTypeOfSales(List<CustomerSale> customerSaleList) {
        return customerSaleList.stream()
                .collect(Collectors.groupingBy(CustomerSale::getTypeOfSale, Collectors.summingInt(customerSale -> customerSale.getAmount().intValueExact())));
    }

    private static int getExporterSalesQuantity(List<ExporterSale> exporterSaleList) {
        return exporterSaleList.stream()
                .mapToInt(ExporterSale::getQuantity)
                .sum();
    }

    private static int getExporterSalesMoney(List<ExporterSale> exporterSaleList) {
        return exporterSaleList.stream()
                .mapToInt(exporterSale -> exporterSale.getCompanyAmount().intValueExact())
                .sum();
    }

    private List<TopSellerDto> getTopSellers(LocalDateTime lastRebootDate) {
        int[] rewards = {
                rewardRepository.findAll().get(0).getRewardAmount(),
                rewardRepository.findAll().get(1).getRewardAmount(),
                rewardRepository.findAll().get(2).getRewardAmount()
        };

        // Récupère toutes les ventes depuis le dernier reboot
        List<ExporterSale> sales = exporterSaleRepository.findAllByDateAfter(lastRebootDate);

        List<Map.Entry<String, Integer>> top3 = sales.stream()
                .collect(Collectors.groupingBy(
                        sale -> sale.getUser().getFirstName() + " " + sale.getUser().getLastName(),
                        Collectors.summingInt(ExporterSale::getQuantity)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .toList();

        List<TopSellerDto> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (i < top3.size()) {
                Map.Entry<String, Integer> entry = top3.get(i);
                result.add(new TopSellerDto(entry.getKey(), entry.getValue(), rewards[i]));
            } else {
                // Pas de vendeur pour cette position
                result.add(new TopSellerDto("—", 0, rewards[i]));
            }
        }
        return result;
    }
}
