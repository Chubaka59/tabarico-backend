package com.gtarp.tabaricobackend.services;

import com.gtarp.tabaricobackend.dto.accounting.CreateExporterSaleDto;
import com.gtarp.tabaricobackend.dto.accounting.ExporterSaleDto;
import com.gtarp.tabaricobackend.entities.accounting.ExporterSale;

import java.util.List;

public interface ExporterSaleService {
    /**
     * find all exporter sales of this week
     * @param username the user who own the exporter sales
     * @return a list of exporter sales
     */
    List<ExporterSaleDto> findAllByUserForCurrentWeek(String username);

    /**
     * find all exporter sales of a user
     * @param username the user who own the exporter sales
     * @return a list of exporter sales
     */
    List<ExporterSale> findAllByUser(String username);

    /**
     * delete a exporter sale
     * @param id the id of the exporter sale
     */
    void delete(int id);

    ExporterSale insert(CreateExporterSaleDto createExporterSaleDto, String username);
}
