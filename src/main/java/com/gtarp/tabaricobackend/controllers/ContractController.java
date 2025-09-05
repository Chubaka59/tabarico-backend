package com.gtarp.tabaricobackend.controllers;

import com.gtarp.tabaricobackend.dto.ContractDto;
import com.gtarp.tabaricobackend.entities.Contract;
import com.gtarp.tabaricobackend.services.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class ContractController {
    @Autowired
    private CrudService<Contract, ContractDto> contractService;

    @GetMapping("/contracts")
    public List<Contract> getContractList() {
        return contractService.getAll();
    }

    @GetMapping("/contracts/{id}")
    public ResponseEntity<Contract> getContract(@PathVariable Integer id) {
        try {
            Contract contract = contractService.getById(id);
            return new ResponseEntity<>(contract, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/contracts")
    public ResponseEntity<Contract> insert(@RequestBody @Validated ContractDto contractDto) {
        try {
            contractService.insert(contractDto);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .build()
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/contracts/{id}")
    public ResponseEntity<Contract> delete(@PathVariable Integer id) {
        try {
            contractService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/contracts/{id}")
    public ResponseEntity<Contract> update(@RequestBody @Validated ContractDto contractDto, @PathVariable Integer id) {
        try {
            contractService.update(id, contractDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
