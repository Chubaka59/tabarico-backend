package com.gtarp.tabaricobackend.controllers;

import com.gtarp.tabaricobackend.dto.accounting.CreateExporterSaleDto;
import com.gtarp.tabaricobackend.dto.accounting.ExporterSaleDto;
import com.gtarp.tabaricobackend.entities.accounting.ExporterSale;
import com.gtarp.tabaricobackend.services.ExporterSaleService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class ExporterSaleController {
    @Autowired
    ExporterSaleService exporterSaleService;

    @GetMapping("/exportersales")
    public List<ExporterSaleDto> getExporterSaleListByDate(@PathParam("date") String date) {
        return exporterSaleService.getExporterSalesDtoListByDate(date);
    }

    @DeleteMapping("/exportersales/{id}")
    public void delete(@PathVariable int id) {
        exporterSaleService.delete(id);
    }

    @PostMapping("/exportersales")
    public ResponseEntity<ExporterSale> insert(@RequestBody CreateExporterSaleDto createExporterSaleDto, Authentication authentication) {
        try {
            String username = authentication.getName();
            exporterSaleService.insert(createExporterSaleDto, username);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .build()
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
