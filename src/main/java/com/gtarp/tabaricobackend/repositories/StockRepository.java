package com.gtarp.tabaricobackend.repositories;

import com.gtarp.tabaricobackend.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> getStockListByDate(LocalDate date);
}
