package com.gtarp.tabaricobackend.services;

import com.gtarp.tabaricobackend.dto.accounting.DashboardDto;
import com.gtarp.tabaricobackend.dto.accounting.PersonalDashboardDto;

import java.util.List;

public interface DashboardService {
    /**
     * get the information for the personal dashboard
     * @param username the user to get the information
     * @return a PersonalDashboardDto
     */
    PersonalDashboardDto getPersonalDashboardDto(String username);

    /**
     * get the information for the general dashboard
     * @return a dashboardDto
     */
    List<DashboardDto> getDashboardDto();

    /**
     * update the dashboard information of a user
     * @param username the username of the user to update
     * @param dashboardDto the information to update
     */
    void updateUser(String username, DashboardDto dashboardDto);

    /**
     * set the blocked status to block the sales
     * @param blocked is the sales blocked
     */
    void setSalesBlocked(boolean blocked);

    /**
     * get the value if the sales are locked
     * @return is the sales locked
     */
    boolean getSalesBlocked();
}
