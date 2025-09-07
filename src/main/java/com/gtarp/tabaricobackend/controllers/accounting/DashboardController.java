package com.gtarp.tabaricobackend.controllers.accounting;

import com.gtarp.tabaricobackend.dto.accounting.PersonalDashboardDto;
import com.gtarp.tabaricobackend.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/personalDashboard")
    public PersonalDashboardDto getPersonalDashboardDto(Authentication authentication) {
        String username = authentication.getName();
        return dashboardService.getPersonalDashboardDto(username);
    }
}
