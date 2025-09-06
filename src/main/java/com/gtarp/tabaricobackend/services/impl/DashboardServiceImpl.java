package com.gtarp.tabaricobackend.services.impl;

import com.gtarp.tabaricobackend.dto.accounting.CustomerSaleDto;
import com.gtarp.tabaricobackend.dto.accounting.ExporterSaleDto;
import com.gtarp.tabaricobackend.dto.accounting.PersonalDashboardDto;
import com.gtarp.tabaricobackend.entities.User;
import com.gtarp.tabaricobackend.services.CustomerSaleService;
import com.gtarp.tabaricobackend.services.DashboardService;
import com.gtarp.tabaricobackend.services.ExporterSaleService;
import com.gtarp.tabaricobackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerSaleService customerSaleService;
    @Autowired
    private ExporterSaleService exporterSaleService;

    public PersonalDashboardDto getPersonalDashboardDto(String username) {
        User user = userService.getByUsername(username);
        List<CustomerSaleDto> customerSaleDtoList = customerSaleService.findAllByUserForCurrentWeek(username);
        List<ExporterSaleDto> exporterSaleDtoList = exporterSaleService.findAllByUserForCurrentWeek(username);
        return new PersonalDashboardDto(user, customerSaleDtoList, exporterSaleDtoList);
    }
}
