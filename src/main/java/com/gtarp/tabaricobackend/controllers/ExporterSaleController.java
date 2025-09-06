package com.gtarp.tabaricobackend.controllers;

import com.gtarp.tabaricobackend.dto.accounting.ExporterSaleDto;
import com.gtarp.tabaricobackend.entities.accounting.ExporterSale;
import com.gtarp.tabaricobackend.services.ExporterSaleService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExporterSaleController {
    @Autowired
    ExporterSaleService exporterSaleService;

    @GetMapping("/exportersales/current-week")
    public List<ExporterSaleDto> findAllByUserAndDateBetween(@PathParam("username") String username) {
        return exporterSaleService.findAllByUserForCurrentWeek(username);
    }

    @GetMapping("/exportersales")
    public List<ExporterSale> findAllByUser(@PathParam("username") String username) {
        return exporterSaleService.findAllByUser(username);
    }

    @DeleteMapping("/exportersale/{id}")
    public void delete(@PathVariable int id) {
        exporterSaleService.delete(id);
    }
}
