package fr.greta.TrackerService.service;


import fr.greta.TrackerService.models.location.VisitedLocation;
import fr.greta.TrackerService.models.user.User;
import fr.greta.TrackerService.models.user.UserReward;


import java.util.List;

public interface UserService {


    List<User> getAllUsers();
    VisitedLocation addToVisitedLocationsOfUser(VisitedLocation visitedLocation, User user);

    void addUserRewardToUser(UserReward newReward , User user);
}
