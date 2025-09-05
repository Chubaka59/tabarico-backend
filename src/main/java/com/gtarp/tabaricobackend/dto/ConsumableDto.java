package com.gtarp.tabaricobackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumableDto {
    private Integer id;
    private String name;
    private Integer quantity;
}
