package com.gtarp.tabaricobackend.repositories.accounting;

import com.gtarp.tabaricobackend.entities.User;
import com.gtarp.tabaricobackend.entities.accounting.ExporterSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExporterSaleRepository extends JpaRepository<ExporterSale, Integer> {
    List<ExporterSale> findAllByUserAndDateBetween(User user, LocalDateTime from, LocalDateTime to);
    List<ExporterSale> findAllByUser(User user);
}