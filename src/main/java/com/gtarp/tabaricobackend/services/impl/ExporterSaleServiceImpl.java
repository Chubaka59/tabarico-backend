package com.gtarp.tabaricobackend.services.impl;

import com.gtarp.tabaricobackend.dto.accounting.CreateExporterSaleDto;
import com.gtarp.tabaricobackend.dto.accounting.ExporterSaleDto;
import com.gtarp.tabaricobackend.entities.User;
import com.gtarp.tabaricobackend.entities.accounting.ExporterSale;
import com.gtarp.tabaricobackend.exception.ExporterSaleNotFoundException;
import com.gtarp.tabaricobackend.exception.SalesLockedException;
import com.gtarp.tabaricobackend.repositories.UserRepository;
import com.gtarp.tabaricobackend.repositories.accounting.AccountingRebootDateRepository;
import com.gtarp.tabaricobackend.repositories.accounting.ExporterSaleRepository;
import com.gtarp.tabaricobackend.services.ExporterSaleService;
import com.gtarp.tabaricobackend.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ExporterSaleServiceImpl implements ExporterSaleService {
    @Autowired
    private ExporterSaleRepository exporterSaleRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountingRebootDateRepository accountingRebootDateRepository;

    public List<ExporterSaleDto> findAllByUserForCurrentWeek(String username) {
        User user = userService.getByUsername(username);

        LocalDateTime lastRebootTime = accountingRebootDateRepository.findAll().getFirst().getAccountingRebootDate();

        return exporterSaleRepository.findAllByUserAndDateAfter(user, lastRebootTime)
                .stream()
                .map(ExporterSaleDto::new)
                .toList();
    }

    private ExporterSale findById(int id) {
        return exporterSaleRepository.findById(id).orElseThrow(() -> new ExporterSaleNotFoundException(id));
    }

    public void delete(int id) {
        if (accountingRebootDateRepository.findAll().getFirst().isSalesLocked()) {
            throw new SalesLockedException();
        }
        ExporterSale exporterSale = findById(id);
        User user = userService.getByUsername(exporterSale.getUser().getUsername());

        //On retire manuellement la vente du user afin de pouvoir le save apres la suppression
        user.getExporterSales().remove(exporterSale);
        exporterSaleRepository.delete(exporterSale);

        //Apres avoir supprimer, on retire la prime et on décoche la quota si il n'est plus fait
        user.setCleanMoneySalary(user.getCleanMoneySalary() - (exporterSale.getCompanyAmount().intValue() * user.getRole().getRedistributionRate() / 100));

        boolean before = user.isQuota() && user.isExporterQuota();
        if (user.getExporterSales().stream().mapToInt(ExporterSale::getQuantity).sum() < 500) {
            user.setExporterQuota(false);
            boolean after = user.isQuota() && user.isExporterQuota();
            if (before && !after) {
                user.setCleanMoneySalary(user.getCleanMoneySalary() - user.getRole().getSalary());
            }
        }
        userRepository.save(user);
    }

    @Transactional
    @Override
    public ExporterSale insert(CreateExporterSaleDto createExporterSaleDto, String username) {
        if (accountingRebootDateRepository.findAll().getFirst().isSalesLocked()) {
            throw new SalesLockedException();
        }
        User user;
        if (createExporterSaleDto.getUserId() != null) {
            user = userService.getById(createExporterSaleDto.getUserId());
        } else {
            user = userService.getByUsername(username);
        }
        ExporterSale exporterSale = new ExporterSale();
        exporterSale.setDate(LocalDateTime.now());
        exporterSale.setLevel(createExporterSaleDto.getLevel() > 100 ? 100 : createExporterSaleDto.getLevel() );
        exporterSale.setQuantity(createExporterSaleDto.getQuantity());
        exporterSale.setUser(user);
        exporterSale.setEmployeeAmount(calculateExporterEmployeeAmount(createExporterSaleDto));
        //le montant employé * 0.3 pour obtenir le montant entreprise
        exporterSale.setCompanyAmount(exporterSale.getEmployeeAmount().multiply(BigDecimal.valueOf(0.3)).setScale(0, RoundingMode.HALF_UP));

        boolean before = user.isQuota() && user.isExporterQuota();
        if (user.getExporterSales().stream().mapToInt(ExporterSale::getQuantity).sum() < 500 && exporterSale.getQuantity() + user.getExporterSales().stream().mapToInt(ExporterSale::getQuantity).sum() >= 500) {
            user.setExporterQuota(true);
            if (user.isQuota()) {
                boolean after = user.isQuota() && user.isExporterQuota();
                if (!before && after) {
                    user.setCleanMoneySalary(user.getCleanMoneySalary() + user.getRole().getSalary());
                }
            }
        }

        //On ajoute la prime au salaire de l'employé
        user.setCleanMoneySalary(user.getCleanMoneySalary() + exporterSale.getCompanyAmount().intValue() * user.getRole().getRedistributionRate() / 100);
        userRepository.save(user);
        return exporterSaleRepository.save(exporterSale);
    }

    @Override
    public List<ExporterSaleDto> getExporterSalesDtoListByDate(String date) {
        return exporterSaleRepository.findAllByDateBetween(LocalDate.parse(date).atStartOfDay(), LocalDate.parse(date).plusDays(1).atStartOfDay())
                .stream()
                .map(ExporterSaleDto::new)
                .collect(Collectors.toList());
    }

    private BigDecimal calculateExporterEmployeeAmount(CreateExporterSaleDto createExporterSaleDto) {
        int level = createExporterSaleDto.getLevel();
        int quantity = createExporterSaleDto.getQuantity();
        // si le level > 100, il n'y a plus de bonus sur le prix
        if (level > 100) {
            level = 100;
        }
        //36 est le prix d'une cigarette de base. on ajoute ensuite un bonus de 0.3% de 36 en fonction du niveau du vendeur et on multiplie par le nombre de cigarette
        return BigDecimal.valueOf((36+(36*(level*0.3/100)))*quantity).setScale(0, RoundingMode.HALF_UP);
    }
}
