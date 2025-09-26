package com.gtarp.tabaricobackend.services.impl;

import com.gtarp.tabaricobackend.dto.accounting.RewardDto;
import com.gtarp.tabaricobackend.entities.accounting.Reward;
import com.gtarp.tabaricobackend.exception.RewardNotFoundException;
import com.gtarp.tabaricobackend.repositories.accounting.RewardRepository;
import com.gtarp.tabaricobackend.services.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardServiceImpl implements RewardService {
    @Autowired
    private RewardRepository rewardRepository;

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    public Reward getById(Integer id) {
        return rewardRepository.findById(id).orElseThrow(() -> new RewardNotFoundException(id));
    }

    public Reward update(Integer id, RewardDto rewardDto) {
        Reward updatedReward = getById(rewardDto.getId()).update(rewardDto);
        return rewardRepository.save(updatedReward);
    }
}
