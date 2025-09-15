package com.gtarp.tabaricobackend.dto.accounting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopSellerDto {
    private String name;
    private Integer quantity;
    private Integer reward;
}
