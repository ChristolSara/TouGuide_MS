package fr.greta.RewardService.controller;

import fr.greta.RewardService.service.RewardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RewardController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RewardController.class);


    private  final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/rewards")
    public int getRewardPoints(@RequestParam final UUID attractionId,@RequestParam final UUID userId){
        LOGGER.info("getting rewards point for attraction  "+ attractionId + " and user "+ userId);
        return  rewardService.getRewardPoints(attractionId,userId);
    }
}
