package com.gtarp.tabaricobackend.dto.accounting;

import com.gtarp.tabaricobackend.entities.User;
import com.gtarp.tabaricobackend.entities.accounting.ExporterSale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExporterSaleDto {
    private Integer id;
    private LocalDateTime date;
    private User user;
    private Integer quantity;
    private Integer level;
    private BigDecimal employeeAmount;
    private BigDecimal companyAmount;
    private boolean verified;

    public ExporterSaleDto(ExporterSale exporterSale) {
        this.id = exporterSale.getId();
        this.date = exporterSale.getDate();
        this.user = exporterSale.getUser();
        this.quantity = exporterSale.getQuantity();
        this.level = exporterSale.getLevel();
        this.employeeAmount = exporterSale.getEmployeeAmount();
        this.companyAmount = exporterSale.getCompanyAmount();
        this.verified = exporterSale.isVerified();
    }
}
