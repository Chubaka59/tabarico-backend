package com.gtarp.tabaricobackend.controllers.accounting;

import com.gtarp.tabaricobackend.dto.accounting.PersonalDashboardDto;
import com.gtarp.tabaricobackend.services.DashboardService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/personalDashboard")
    public PersonalDashboardDto getPersonalDashboardDto(@PathParam("username") String username) {
        return dashboardService.getPersonalDashboardDto(username);
    }
}
