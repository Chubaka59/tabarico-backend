package com.gtarp.tabaricobackend.entities.accounting;

import com.gtarp.tabaricobackend.dto.accounting.RewardDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String position;
    private Integer rewardAmount;

    public Reward update(RewardDto rewardDto) {
        this.rewardAmount = rewardDto.getRewardAmount();
        return this;
    }
}
