package com.gtarp.tabaricobackend.entities;

import com.gtarp.tabaricobackend.dto.CustomerDirtySaleRateDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDirtySaleRate implements UpdatableEntity<CustomerDirtySaleRate, CustomerDirtySaleRateDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Integer customerDirtySaleRate;

    @Override
    public CustomerDirtySaleRate update(CustomerDirtySaleRateDto customerDirtySaleRateDto) {
        this.customerDirtySaleRate = customerDirtySaleRateDto.getCustomerDirtySaleRate();
        return this;
    }
}
