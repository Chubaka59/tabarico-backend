package com.gtarp.tabaricobackend.dto.accounting;

import com.gtarp.tabaricobackend.entities.accounting.TypeOfSale;
import lombok.Data;

@Data
public class CreateCustomerSaleDto {
    private Integer userId;
    private Integer product;
    private TypeOfSale typeOfSale;
    private Integer contract;
    private Integer quantity;
    private Integer price;
}
