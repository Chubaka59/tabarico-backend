package com.gtarp.tabaricobackend.entities.accounting;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gtarp.tabaricobackend.entities.Contract;
import com.gtarp.tabaricobackend.entities.Product;
import com.gtarp.tabaricobackend.entities.Stock;
import com.gtarp.tabaricobackend.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private TypeOfSale typeOfSale;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "stock_id")
    private Stock stock;
}
