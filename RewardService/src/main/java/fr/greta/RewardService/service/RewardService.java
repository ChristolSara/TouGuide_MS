package fr.greta.RewardService.service;

import java.util.UUID;

public interface RewardService  {
    //calculler les points on utilisant attractionid et userid
    int getRewardPoints(UUID attractionId,UUID userId);
}
