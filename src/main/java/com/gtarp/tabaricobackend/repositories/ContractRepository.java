package com.gtarp.tabaricobackend.repositories;

import com.gtarp.tabaricobackend.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    Optional<Contract> findContractByCompany(String company);
}
