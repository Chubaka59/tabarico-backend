package com.gtarp.tabaricobackend.services.impl;

import com.gtarp.tabaricobackend.dto.ContractDto;
import com.gtarp.tabaricobackend.entities.Contract;
import com.gtarp.tabaricobackend.exception.ContractAlreadyExistException;
import com.gtarp.tabaricobackend.exception.ContractNotFoundException;
import com.gtarp.tabaricobackend.repositories.ContractRepository;
import com.gtarp.tabaricobackend.services.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractServiceImpl extends AbstractCrudService<Contract, ContractRepository, ContractDto> {

    public ContractServiceImpl(ContractRepository repository) {
        super(repository);
    }

    @Override
    public Contract getById(Integer id) {
        return this.repository.findById(id).orElseThrow(() -> new ContractNotFoundException(id));
    }

    @Override
    public Contract insert(ContractDto contractDto) {
        Optional<Contract> existingContract = this.repository.findContractByCompany(contractDto.getCompany());
        if (existingContract.isPresent()) {
            throw new ContractAlreadyExistException(contractDto.getCompany());
        }
        Contract newContract = new Contract(contractDto);
        return this.repository.save(newContract);
    }
}
