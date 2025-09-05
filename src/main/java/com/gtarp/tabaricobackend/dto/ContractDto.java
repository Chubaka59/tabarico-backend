package com.gtarp.tabaricobackend.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {
    private Integer id;
    @NotBlank
    private String company;
    @NotNull
    private Integer reduction;
}
