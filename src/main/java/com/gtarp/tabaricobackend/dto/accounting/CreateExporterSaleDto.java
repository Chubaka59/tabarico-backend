package com.gtarp.tabaricobackend.dto.accounting;

import lombok.Data;

@Data
public class CreateExporterSaleDto {
    private Integer userId;
    private Integer quantity;
    private Integer level;
}
