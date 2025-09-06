package com.gtarp.tabaricobackend.dto.accounting;

import com.gtarp.tabaricobackend.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalDashboardDto {
    private Integer cleanMoneySalary;
    private Integer dirtyMoneySalary;
    private Integer cleanMoneySalaryPreviousWeek;
    private Integer dirtyMoneySalaryPreviousWeek;
    private boolean quota;
    private boolean exporterQuota;
    private List<CustomerSaleDto> customerSaleDtoList;
    private List<ExporterSaleDto> exporterSaleDtoList;

    public PersonalDashboardDto(User user, List<CustomerSaleDto> customerSaleDtoList, List<ExporterSaleDto> exporterSaleDtoList) {
        cleanMoneySalary = user.getCleanMoneySalary();
        dirtyMoneySalary = user.getDirtyMoneySalary();
        cleanMoneySalaryPreviousWeek = user.getCleanMoneySalaryPreviousWeek();
        dirtyMoneySalaryPreviousWeek = user.getDirtyMoneySalaryPreviousWeek();
        quota = user.isQuota();
        exporterQuota = user.isExporterQuota();
        this.customerSaleDtoList = customerSaleDtoList;
        this.exporterSaleDtoList = exporterSaleDtoList;
    }
}
