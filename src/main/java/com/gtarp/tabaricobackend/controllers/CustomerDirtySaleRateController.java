package com.gtarp.tabaricobackend.controllers;

import com.gtarp.tabaricobackend.dto.CustomerDirtySaleRateDto;
import com.gtarp.tabaricobackend.entities.CustomerDirtySaleRate;
import com.gtarp.tabaricobackend.services.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CustomerDirtySaleRateController {
    @Autowired
    private CrudService<CustomerDirtySaleRate, CustomerDirtySaleRateDto> customerDirtySaleRateService;

    @GetMapping("/customerDirtySaleRates")
    public List<CustomerDirtySaleRate> getAll() {
        return customerDirtySaleRateService.getAll();
    }

    @GetMapping("/customerDirtySaleRates/{id}")
    public ResponseEntity<CustomerDirtySaleRate> getCustomerDirtySaleRate(@PathVariable Integer id) {
        try {
            CustomerDirtySaleRate customerDirtySaleRate = customerDirtySaleRateService.getById(id);
            return new ResponseEntity<>(customerDirtySaleRate, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/customerDirtySaleRates/{id}")
    public ResponseEntity<CustomerDirtySaleRate> update(@RequestBody @Validated CustomerDirtySaleRateDto customerDirtySaleRateDto, @PathVariable Integer id) {
        try {
            customerDirtySaleRateService.update(id, customerDirtySaleRateDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
