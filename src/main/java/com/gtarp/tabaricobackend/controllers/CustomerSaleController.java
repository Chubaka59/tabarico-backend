package com.gtarp.tabaricobackend.controllers;

import com.gtarp.tabaricobackend.dto.accounting.CreateCustomerSaleDto;
import com.gtarp.tabaricobackend.entities.accounting.CustomerSale;
import com.gtarp.tabaricobackend.entities.accounting.TypeOfSale;
import com.gtarp.tabaricobackend.services.CustomerSaleService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class CustomerSaleController {
    @Autowired
    CustomerSaleService customerSaleService;

    @GetMapping("/customersales")
    public List<CustomerSale> findAllByUser(@PathParam("username") String username) {
        return customerSaleService.findAllByUser(username);
    }

    @DeleteMapping("/customersale/{id}")
    public void delete(@PathVariable int id) {
        customerSaleService.delete(id);
    }

    @GetMapping("/customersales/saletypes")
    public TypeOfSale[] getSalesTypes() {
        return customerSaleService.getTypeOfSales();
    }

    @PostMapping("/customersales")
    public ResponseEntity<CustomerSale> create(@RequestBody CreateCustomerSaleDto createCustomerSaleDto, Authentication authentication) {
        try {
            String username = authentication.getName();
            customerSaleService.insert(createCustomerSaleDto, username);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .build()
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
