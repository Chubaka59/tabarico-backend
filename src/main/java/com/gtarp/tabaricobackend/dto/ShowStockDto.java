package com.gtarp.tabaricobackend.dto;

import com.gtarp.tabaricobackend.entities.Consumable;
import com.gtarp.tabaricobackend.entities.Product;
import com.gtarp.tabaricobackend.entities.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowStockDto {
    private Integer id;
    private LocalDateTime date;
    private String typeOfStockMovement;
    private Product product;
    private Consumable consumable;
    private Integer quantityMouvement;
    private String userName;

    public ShowStockDto(Stock stock) {
        this.id = stock.getId();
        this.date = stock.getDate();
        this.typeOfStockMovement = stock.getTypeOfStockMovement().toString();
        this.product = stock.getProduct();
        this.consumable = stock.getConsumable();
        this.quantityMouvement = stock.getQuantityMouvement();
        this.userName = stock.getUser().toString();
    }
}
