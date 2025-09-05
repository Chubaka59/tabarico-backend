package com.gtarp.tabaricobackend.services.impl;

import com.gtarp.tabaricobackend.dto.CustomerDirtySaleRateDto;
import com.gtarp.tabaricobackend.entities.CustomerDirtySaleRate;
import com.gtarp.tabaricobackend.exception.CustomerDirtySaleRateNotFoundException;
import com.gtarp.tabaricobackend.repositories.CustomerDirtySaleRateRepository;
import com.gtarp.tabaricobackend.services.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class CustomerDirtySaleRateServiceImpl extends AbstractCrudService<CustomerDirtySaleRate, CustomerDirtySaleRateRepository, CustomerDirtySaleRateDto> {

    public CustomerDirtySaleRateServiceImpl(CustomerDirtySaleRateRepository repository) {
        super(repository);
    }

    @Override
    public CustomerDirtySaleRate getById(Integer id) {
        return this.repository.findById(id).orElseThrow(() -> new CustomerDirtySaleRateNotFoundException(id));
    }

    @Override
    public CustomerDirtySaleRate insert(CustomerDirtySaleRateDto customerDirtySaleRateDto) {
        return null;
    }
}
