package com.gtarp.tabaricobackend.entities.accounting;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class ExporterSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    private Integer quantity;
    private Integer level;
    private BigDecimal employeeAmount;
    private BigDecimal companyAmount;
    private boolean verified;

    public ExporterSale verifySale(boolean verified) {
        this.verified = verified;
        return this;
    }
}
