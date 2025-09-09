package com.gtarp.tabaricobackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {
    private Integer productId;
    @NotNull
    private Integer quantity;
}
