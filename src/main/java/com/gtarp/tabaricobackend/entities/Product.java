package com.gtarp.tabaricobackend.entities;

import com.gtarp.tabaricobackend.dto.ProductDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Product implements UpdatableEntity<Product, ProductDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String name;
    @Digits(integer = 3, fraction = 0)
    private Integer cleanMoney;
    @Digits(integer = 3, fraction = 0)
    private Integer dirtyMoney;
    private Integer stock;

    public Product(ProductDto productDto) {
        this.name = productDto.getName();
        this.cleanMoney = productDto.getCleanMoney();
        this.dirtyMoney = productDto.getDirtyMoney();
        this.stock = 0;
    }

    public Product update(ProductDto productDto) {
        this.name = productDto.getName();
        this.cleanMoney = productDto.getCleanMoney();
        this.dirtyMoney = productDto.getDirtyMoney();
        return this;
    }
}
