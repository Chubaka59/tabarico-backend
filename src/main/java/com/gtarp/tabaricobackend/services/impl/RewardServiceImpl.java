package com.gtarp.tabaricobackend.services.impl;

import com.gtarp.tabaricobackend.dto.accounting.RewardDto;
import com.gtarp.tabaricobackend.entities.accounting.AccountingRebootInformation;
import com.gtarp.tabaricobackend.repositories.accounting.AccountingRebootInformationRepository;
import com.gtarp.tabaricobackend.services.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardServiceImpl implements RewardService {
    @Autowired
    private AccountingRebootInformationRepository accountingRebootInformationRepository;

    public RewardDto[] getAllRewards() {
        AccountingRebootInformation accountingRebootInformation = accountingRebootInformationRepository.findAll().getFirst();
        return new RewardDto[]{new RewardDto("1er", accountingRebootInformation.getTop1Reward()), new RewardDto("2er", accountingRebootInformation.getTop2Reward()), new RewardDto("3er", accountingRebootInformation.getTop3Reward())};
    }

    @Override
    public void setRewards(RewardDto rewards, String position) {
        AccountingRebootInformation accountingRebootInformation = accountingRebootInformationRepository.findAll().getFirst();
        if (position.equals("1er")) {
            accountingRebootInformation.setTop1Reward(rewards.getAmount());
        } else if (position.equals("2er")) {
            accountingRebootInformation.setTop2Reward(rewards.getAmount());
        } else if (position.equals("3er")) {
            accountingRebootInformation.setTop3Reward(rewards.getAmount());
        }
        accountingRebootInformationRepository.save(accountingRebootInformation);
    }
}
