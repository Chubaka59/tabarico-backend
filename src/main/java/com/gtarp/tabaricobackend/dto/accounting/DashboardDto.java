package com.gtarp.tabaricobackend.dto.accounting;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DashboardDto {
    private String roleName;
    private String username;
    private Integer cleanMoneyCustomerSales;
    private Integer dirtyMoneyCustomerSales;
    private Integer enterpriseAmountExporterSales;
    private Integer quantityExporterSales;
    private boolean quota;
    private boolean exporterQuota;
    private Integer cleanMoneySalary;
    private Integer dirtyMoneySalary;
    private Integer cleanMoneySalaryPreviousWeek;
    private Integer dirtyMoneySalaryPreviousWeek;
    private boolean holiday;
    private LocalDate endOfHoliday;
    private boolean warning1;
    private boolean warning2;
}
