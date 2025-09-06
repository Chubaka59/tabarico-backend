package com.gtarp.tabaricobackend.services;

import com.gtarp.tabaricobackend.dto.accounting.CustomerSaleDto;
import com.gtarp.tabaricobackend.entities.accounting.CustomerSale;

import java.util.List;

public interface CustomerSaleService {
    /**
     * find all customer sales for the current week
     * @param username the user who own the customer sales
     * @return a list of customer sales
     */
    List<CustomerSaleDto> findAllByUserForCurrentWeek(String username);

    /**
     * find all customer sales of a user
     * @param username the user who own the customer sales
     * @return a list of customer sales
     */
    List<CustomerSale> findAllByUser(String username);

    /**
     * delete a customer sale
     * @param id the id of the customer sale
     */
    public void delete(int id);
}
