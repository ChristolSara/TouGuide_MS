package fr.greta.TrackerService.service;

import fr.greta.TrackerService.models.src.main.java.org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import fr.greta.TrackerService.models.src.main.java.org.openclassrooms.tourguide.models.model.user.User;
import fr.greta.TrackerService.models.src.main.java.org.openclassrooms.tourguide.models.model.user.UserReward;

import java.util.List;

public interface UserService {


    List<User> getAllUsers();
    VisitedLocation addToVisitedLocationsOfUser(VisitedLocation visitedLocation,User user);

    void addUserRewardToUser(UserReward newReward ,User user);
}
