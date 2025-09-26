package com.gtarp.tabaricobackend.dto.accounting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RewardDto {
    private Integer id;
    private String position;
    private Integer rewardAmount;
}
