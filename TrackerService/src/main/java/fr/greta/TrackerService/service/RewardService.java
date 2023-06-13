package fr.greta.TrackerService.service;


import fr.greta.TrackerService.models.location.Attraction;
import fr.greta.TrackerService.models.user.User;


public interface RewardService {
    int getAttractionsRewardPoint(final Attraction attraction, final User user);
}
