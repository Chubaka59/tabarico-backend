package com.gtarp.tabaricobackend.controllers;

import com.gtarp.tabaricobackend.dto.ShowStockDto;
import com.gtarp.tabaricobackend.dto.StockDto;
import com.gtarp.tabaricobackend.entities.Stock;
import com.gtarp.tabaricobackend.services.StockService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("/stocks")
    public ResponseEntity<Stock> insert(@RequestBody @Validated StockDto stockDto, Authentication authentication) {
        try {
            if (stockDto.getProductId() != null && stockDto.getConsumableId() != null) {
                log.error("Il ne faut spécifié qu'un seul type à mettre à jour");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            stockService.insert(stockDto, authentication.getName());
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .build()
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            log.error("Modification du stock impossible", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/stocks")
    public List<ShowStockDto> getStockDtoListByDate(@PathParam("date") String date) {
        return stockService.getStockDtoListByDate(date);
    }
}
