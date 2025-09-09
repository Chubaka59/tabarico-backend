package com.gtarp.tabaricobackend.services;

import com.gtarp.tabaricobackend.dto.StockDto;
import com.gtarp.tabaricobackend.entities.Stock;

import java.util.List;

public interface StockService {
    /**
     * insert a new stock mouvement
     * @param stockDto the information of the mouvement
     */
    void insert(StockDto stockDto, String username);

    /**
     * get a list of stock mouvement by date
     * @param date the date of the stock mouvements
     * @return a list of StockDto
     */
    List<Stock> getStockDtoListByDate(String date);
}
