package com.gtarp.tabaricobackend.services;

import com.gtarp.tabaricobackend.dto.accounting.RewardDto;
import com.gtarp.tabaricobackend.entities.accounting.Reward;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RewardService {
    List<Reward> getAllRewards();
    Reward getById(Integer id);
    Reward update(Integer id, RewardDto rewardDto);
}
