package com.gtarp.tabaricobackend.repositories.accounting;

import com.gtarp.tabaricobackend.entities.accounting.AccountingRebootInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountingRebootInformationRepository extends JpaRepository<AccountingRebootInformation, Integer> {
}
