package com.gtarp.tabaricobackend.repositories.accounting;

import com.gtarp.tabaricobackend.entities.accounting.AccountingRebootDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountingRebootDateRepository extends JpaRepository<AccountingRebootDate, Integer> {
}
