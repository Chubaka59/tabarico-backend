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
     * delete a exporter sale
     * @param id the id of the exporter sale
     */
    void delete(int id);

    /**
     * create an exporter sale
     * @param createExporterSaleDto the information of the exporter sale
     * @param username the username of the user who own the sale
     * @return the exporter sale
     */
    ExporterSale insert(CreateExporterSaleDto createExporterSaleDto, String username);

    /**
     * get an exporter list by its date
     * @param date the date to filter
     * @return a list of exporter sale
     */
    List<ExporterSaleDto> getExporterSalesDtoListByDate(String date);

    /**
     * validate if a sales is verified or not
     * @param id the id of the sales
     * @param verified a boolean if verified or not
     */
    void verifySale(int id, boolean verified);
}
