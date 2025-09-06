package com.gtarp.tabaricobackend.services.impl;

import com.gtarp.tabaricobackend.dto.accounting.CustomerSaleDto;
import com.gtarp.tabaricobackend.entities.User;
import com.gtarp.tabaricobackend.entities.accounting.CustomerSale;
import com.gtarp.tabaricobackend.exception.CustomerSaleNotFoundException;
import com.gtarp.tabaricobackend.repositories.accounting.CustomerSaleRepository;
import com.gtarp.tabaricobackend.services.CustomerSaleService;
import com.gtarp.tabaricobackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class CustomerSaleServiceImpl implements CustomerSaleService {
    @Autowired
    private CustomerSaleRepository customerSaleRepository;
    @Autowired
    private UserService userService;

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
}
