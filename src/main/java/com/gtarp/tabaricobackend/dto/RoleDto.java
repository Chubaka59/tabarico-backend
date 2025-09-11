package com.gtarp.tabaricobackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Integer id;
    @NotBlank
    private String name;
    @NotNull
    private Integer redistributionRate;
    @NotNull
    private Integer salary;
}
