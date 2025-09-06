package com.gtarp.tabaricobackend.services;

import com.gtarp.tabaricobackend.dto.accounting.PersonalDashboardDto;

public interface DashboardService {
    /**
     * get the information for the personal dashboard
     * @param username the user to get the information
     * @return a PersonalDashboardDto
     */
    PersonalDashboardDto getPersonalDashboardDto(String username);
}
