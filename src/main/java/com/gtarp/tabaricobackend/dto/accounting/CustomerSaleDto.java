package com.gtarp.tabaricobackend.dto.accounting;

import com.gtarp.tabaricobackend.entities.Contract;
import com.gtarp.tabaricobackend.entities.Product;
import com.gtarp.tabaricobackend.entities.User;
import com.gtarp.tabaricobackend.entities.accounting.CustomerSale;
import com.gtarp.tabaricobackend.entities.accounting.TypeOfSale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaleDto {
    private Integer id;
    private LocalDateTime date;
    private Product product;
    private String typeOfSale;
    private Integer quantity;
    private Contract contract;
    private BigDecimal amount;
    private User user;

    public CustomerSaleDto(CustomerSale customerSale){
        this.id = customerSale.getId();
        this.date = customerSale.getDate();
        this.product = customerSale.getProduct();
        this.typeOfSale = (customerSale.getTypeOfSale() == TypeOfSale.cleanMoney ? TypeOfSale.cleanMoney.toString() : TypeOfSale.dirtyMoney.toString()) ;
        this.quantity = customerSale.getQuantity();
        this.contract = customerSale.getContract();
        this.amount = customerSale.getAmount();
        this.user = customerSale.getUser();
    }
}
