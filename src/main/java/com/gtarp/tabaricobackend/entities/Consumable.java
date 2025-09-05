package com.gtarp.tabaricobackend.entities;

import com.gtarp.tabaricobackend.dto.ConsumableDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Consumable implements UpdatableEntity<Consumable, ConsumableDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String name;
    private Integer quantity;

    public Consumable(ConsumableDto consumableDto) {
        this.name = consumableDto.getName();
        this.quantity = 0;
    }

    public Consumable update (ConsumableDto consumableDto) {
        this.name = consumableDto.getName();
        return this;
    }
}
