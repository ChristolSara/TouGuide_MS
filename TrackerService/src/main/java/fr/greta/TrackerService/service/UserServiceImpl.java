package fr.greta.TrackerService.service;

import fr.greta.TrackerService.models.src.main.java.org.openclassrooms.tourguide.models.model.location.VisitedLocation;
import fr.greta.TrackerService.models.src.main.java.org.openclassrooms.tourguide.models.model.user.User;
import fr.greta.TrackerService.models.src.main.java.org.openclassrooms.tourguide.models.model.user.UserReward;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class UserServiceImpl implements  UserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private  final WebClient webClientUserApi;

    public UserServiceImpl(@Qualifier("getWebClientUserApi")final WebClient webClientUserApi1) {
        this.webClientUserApi = webClientUserApi1;
    }

    @Override
    public List<User> getAllUsers() {

        LOGGER.info("getting all users");
        return webClientUserApi
                .get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .block();
    }

    @Override
    public VisitedLocation addToVisitedLocationsOfUser(VisitedLocation visitedLocation, User user) {
        LOGGER.info("Adding visited location "+ visitedLocation + " to user "+user);
        List<VisitedLocation> visitedLocations = user.getVisitedLocations();
        visitedLocations.add(visitedLocation);
        return visitedLocation;
    }

    @Override
    public void addUserRewardToUser(UserReward newReward, User user) {
        LOGGER.info("adding user reward "+ newReward+" to user "+user);
        List<UserReward> userRewards = user.getUserRewards();

        if(userRewards
                .stream()
                .noneMatch(userReward ->
                        userReward.getAttraction().getAttractionName().equals(newReward.getAttraction().getAttractionName()))){
            userRewards.add(newReward);
        }

    }
}
