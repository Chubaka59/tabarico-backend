package com.gtarp.tabaricobackend.services;

import com.gtarp.tabaricobackend.dto.accounting.RewardDto;
import org.springframework.stereotype.Service;

@Service
public interface RewardService {
    RewardDto[] getAllRewards();
    void setRewards(RewardDto rewards, String position);
}
