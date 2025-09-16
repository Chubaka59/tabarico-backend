package com.gtarp.tabaricobackend.controllers;

import com.gtarp.tabaricobackend.dto.accounting.RewardDto;
import com.gtarp.tabaricobackend.services.RewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class RewardController {
    @Autowired
    private RewardService rewardService;

    @GetMapping("/rewards")
    public RewardDto[] getRoleList() {
        return rewardService.getAllRewards();
    }

    @PutMapping("/rewards/{position}")
    public ResponseEntity<RewardDto> update(@RequestBody @Validated RewardDto rewardDto, @PathVariable String position) {
        try {
            rewardService.setRewards(rewardDto, position);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
