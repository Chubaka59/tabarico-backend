package com.gtarp.tabaricobackend.services.impl;

import com.gtarp.tabaricobackend.dto.ContractDto;
import com.gtarp.tabaricobackend.dto.CustomerDirtySaleRateDto;
import com.gtarp.tabaricobackend.dto.ProductDto;
import com.gtarp.tabaricobackend.dto.accounting.CreateCustomerSaleDto;
import com.gtarp.tabaricobackend.dto.accounting.CustomerSaleDto;
import com.gtarp.tabaricobackend.entities.Contract;
import com.gtarp.tabaricobackend.entities.CustomerDirtySaleRate;
import com.gtarp.tabaricobackend.entities.Product;
import com.gtarp.tabaricobackend.entities.User;
import com.gtarp.tabaricobackend.entities.accounting.CustomerSale;
import com.gtarp.tabaricobackend.entities.accounting.TypeOfSale;
import com.gtarp.tabaricobackend.exception.CustomerSaleInformationErrorException;
import com.gtarp.tabaricobackend.exception.CustomerSaleNotFoundException;
import com.gtarp.tabaricobackend.repositories.UserRepository;
import com.gtarp.tabaricobackend.repositories.accounting.CustomerSaleRepository;
import com.gtarp.tabaricobackend.services.CrudService;
import com.gtarp.tabaricobackend.services.CustomerSaleService;
import com.gtarp.tabaricobackend.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@Transactional
public class CustomerSaleServiceImpl implements CustomerSaleService {
    @Autowired
    private CustomerSaleRepository customerSaleRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CrudService<Product, ProductDto> productService;
    @Autowired
    private CrudService<Contract, ContractDto> contractService;
    @Autowired
    private CrudService<CustomerDirtySaleRate, CustomerDirtySaleRateDto> customerDirtySaleRateService;
    @Autowired
    private UserRepository userRepository;

    public List<CustomerSaleDto> findAllByUserForCurrentWeek(String username) {
        User user = userService.getByUsername(username);

        LocalDate today = LocalDate.now();
        LocalDateTime startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)).atTime(4, 0, 0, 0);
        LocalDateTime endOfWeek = startOfWeek.plusWeeks(1).withHour(3).withMinute(59).withSecond(59).withNano(999999999);

        return customerSaleRepository.findAllByUserAndDateBetween(user, startOfWeek, endOfWeek)
                .stream()
                .map(CustomerSaleDto::new)
                .toList();
    }

    public List<CustomerSale> findAllByUser(String username) {
        User user = userService.getByUsername(username);
        return customerSaleRepository.findAllByUser(user);
    }

    private CustomerSale findById(int id) {
        return customerSaleRepository.findById(id).orElseThrow(() -> new CustomerSaleNotFoundException(id));
    }

    public void delete(int id) {
        CustomerSale customerSale = findById(id);
        customerSaleRepository.delete(customerSale);
    }

    public TypeOfSale[] getTypeOfSales() {
        return TypeOfSale.values();
    }

    public CustomerSale insert(CreateCustomerSaleDto createCustomerSaleDto, String username) {
        User user = userService.getByUsername(username);
        CustomerSale customerSale = new CustomerSale();
        customerSale.setDate(LocalDateTime.now());
        customerSale.setProduct(productService.getById(createCustomerSaleDto.getProduct()));
        customerSale.setTypeOfSale(createCustomerSaleDto.getTypeOfSale());
        customerSale.setQuantity(createCustomerSaleDto.getQuantity());
        if (createCustomerSaleDto.getContract() != null) {
            customerSale.setContract(contractService.getById(createCustomerSaleDto.getContract()));
        }
        customerSale.setAmount(calculateCustomerSaleAmount(createCustomerSaleDto));
        if (customerSale.getAmount().intValue() != createCustomerSaleDto.getPrice()) {
            throw new CustomerSaleInformationErrorException(username);
        }
        customerSale.setUser(user);
        if (createCustomerSaleDto.getTypeOfSale() == TypeOfSale.cleanMoney) {
            user.setCleanMoneySalary(user.getCleanMoneySalary() + (createCustomerSaleDto.getPrice() * user.getRole().getRedistributionRate() / 100));
            userRepository.save(user);
        } else {
            user.setDirtyMoneySalary(user.getDirtyMoneySalary() + (createCustomerSaleDto.getPrice() * customerDirtySaleRateService.getById(1).getCustomerDirtySaleRate() / 100));
            userRepository.save(user);
        }

        return customerSaleRepository.save(customerSale);
    }

    private BigDecimal calculateCustomerSaleAmount(CreateCustomerSaleDto createCustomerSaleDto) {
        BigDecimal pricePerUnit;
        if (createCustomerSaleDto.getTypeOfSale().equals(TypeOfSale.cleanMoney)) {
            pricePerUnit = new BigDecimal(productService.getById(createCustomerSaleDto.getProduct()).getCleanMoney());
            //la reduction des contrats ne s'applique que sur les ventes en propre
            if(createCustomerSaleDto.getContract() != null) {
                BigDecimal reduction = new BigDecimal(contractService.getById(createCustomerSaleDto.getContract()).getReduction());
                pricePerUnit = pricePerUnit.subtract((pricePerUnit.multiply(reduction).divide(BigDecimal.valueOf(100))));
            }
        } else {
            pricePerUnit = new BigDecimal(productService.getById(createCustomerSaleDto.getProduct()).getDirtyMoney());
        }

        return pricePerUnit.multiply(BigDecimal.valueOf(createCustomerSaleDto.getQuantity())).setScale(0, RoundingMode.HALF_UP);
    }
}
