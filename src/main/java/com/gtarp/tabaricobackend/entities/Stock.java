package com.gtarp.tabaricobackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private TypeOfStockMovement typeOfStockMovement;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer quantityMouvement;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
