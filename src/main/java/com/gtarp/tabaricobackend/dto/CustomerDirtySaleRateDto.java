package com.gtarp.tabaricobackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDirtySaleRateDto {
    private Integer id;
    @NotNull
    private Integer customerDirtySaleRate;
}
