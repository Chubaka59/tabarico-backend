package com.gtarp.tabaricobackend.controllers;

import com.gtarp.tabaricobackend.dto.accounting.CustomerSaleDto;
import com.gtarp.tabaricobackend.entities.accounting.CustomerSale;
import com.gtarp.tabaricobackend.services.CustomerSaleService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerSaleController {
    @Autowired
    CustomerSaleService customerSaleService;

    @GetMapping("/customersales/current-week")
    public List<CustomerSaleDto> findAllByUserAndDateBetween(@PathParam("username") String username) {
        return customerSaleService.findAllByUserForCurrentWeek(username);
    }

    @GetMapping("/customersales")
    public List<CustomerSale> findAllByUser(@PathParam("username") String username) {
        return customerSaleService.findAllByUser(username);
    }

    @DeleteMapping("/customersale/{id}")
    public void delete(@PathVariable int id) {
        customerSaleService.delete(id);
    }
}
