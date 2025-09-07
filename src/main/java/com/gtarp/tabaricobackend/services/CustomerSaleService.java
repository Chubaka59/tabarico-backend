package com.gtarp.tabaricobackend.services;

import com.gtarp.tabaricobackend.dto.accounting.CreateCustomerSaleDto;
import com.gtarp.tabaricobackend.dto.accounting.CustomerSaleDto;
import com.gtarp.tabaricobackend.entities.accounting.CustomerSale;
import com.gtarp.tabaricobackend.entities.accounting.TypeOfSale;

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

    /**
     * get all type of sales
     * @return return an array with all type of sales
     */
    TypeOfSale[] getTypeOfSales();

    /**
     * create a customer sale
     * @param createCustomerSaleDto the information of the customer sale
     * @param username the username of the user who own the sale
     * @return the customer sale
     */
    CustomerSale insert(CreateCustomerSaleDto createCustomerSaleDto, String username);
}
