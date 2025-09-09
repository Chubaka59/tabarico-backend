package com.gtarp.tabaricobackend.controllers.accounting;

import com.gtarp.tabaricobackend.dto.accounting.DashboardDto;
import com.gtarp.tabaricobackend.dto.accounting.PersonalDashboardDto;
import com.gtarp.tabaricobackend.services.DashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/personalDashboard")
    public PersonalDashboardDto getPersonalDashboardDto(Authentication authentication) {
        String username = authentication.getName();
        return dashboardService.getPersonalDashboardDto(username);
    }

    @GetMapping("/dashboard")
    public List<DashboardDto> getDashboardDto() {
        return dashboardService.getDashboardDto();
    }

    @PutMapping("/dashboard/{username}")
    public ResponseEntity<DashboardDto> updateDashboardDto(@PathVariable String username, @RequestBody DashboardDto dashboardDto) {
        try {
            dashboardService.updateUser(username, dashboardDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
