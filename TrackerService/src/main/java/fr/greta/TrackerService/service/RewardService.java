package fr.greta.TrackerService.service;

import fr.greta.TrackerService.models.src.main.java.org.openclassrooms.tourguide.models.model.location.Attraction;
import fr.greta.TrackerService.models.src.main.java.org.openclassrooms.tourguide.models.model.user.User;

public interface RewardService {
    int getAttractionsRewardPoint(final Attraction attraction,final User user);
}
