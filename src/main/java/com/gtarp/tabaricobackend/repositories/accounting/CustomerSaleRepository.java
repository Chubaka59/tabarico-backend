package com.gtarp.tabaricobackend.repositories.accounting;

import com.gtarp.tabaricobackend.entities.User;
import com.gtarp.tabaricobackend.entities.accounting.CustomerSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerSaleRepository extends JpaRepository<CustomerSale, Integer> {
    List<CustomerSale> findAllByUserAndDateBetween(User user, LocalDateTime from, LocalDateTime to);
    List<CustomerSale> findAllByDateBetween(LocalDateTime start, LocalDateTime end);
    List<CustomerSale> findAllByUserAndDateAfter(User user, LocalDateTime to);
}
