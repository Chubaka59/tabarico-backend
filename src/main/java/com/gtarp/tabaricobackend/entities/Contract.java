package com.gtarp.tabaricobackend.entities;

import com.gtarp.tabaricobackend.dto.ContractDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Contract implements UpdatableEntity<Contract, ContractDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String company;
    @NotNull
    private Integer reduction;

    public Contract(ContractDto contractDto) {
        this.company = contractDto.getCompany();
        this.reduction = contractDto.getReduction();
    }

    public Contract update(ContractDto contractDto) {
        this.company = contractDto.getCompany();
        this.reduction = contractDto.getReduction();
        return this;
    }
}
