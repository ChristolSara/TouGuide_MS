package fr.greta.TrackerService.executer;

import fr.greta.TrackerService.models.src.main.java.org.openclassrooms.tourguide.models.model.location.Attraction;
import fr.greta.TrackerService.models.src.main.java.org.openclassrooms.tourguide.models.model.user.User;
import fr.greta.TrackerService.models.src.main.java.org.openclassrooms.tourguide.models.model.user.UserReward;
import fr.greta.TrackerService.service.LocationService;
import fr.greta.TrackerService.service.RewardService;
import fr.greta.TrackerService.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class RewardExecutorImpl implements  RewardExecutor {
    private static  final Logger LOGGER = LoggerFactory.getLogger(RewardExecutor.class);
    private final LocationService locationService;
    private final RewardService rewardService;
    private final UserService userService;

    private final ExecutorService executor;

    public RewardExecutorImpl(final LocationService locationService1,final RewardService rewardService1,final UserService userService1) {
        locationService = locationService1;
       rewardService = rewardService1;
        userService = userService1;
      executor = Executors.newFixedThreadPool(16);
    }


    @Override
    public CompletableFuture<?> calculateRewards(User user) {

        LOGGER.info("calculating rewards for user  "+user);

        List<UserReward> userRewards = new ArrayList<>(user.getUserRewards());

        return CompletableFuture.runAsync(() ->
                new ArrayList<>(user.getVisitedLocations())
                        .forEach(visitedLocation ->
                                locationService.getAttractionNearVisitedLocation(visitedLocation)
                                        .stream()
                                        .filter( attraction ->
                                                isAttractionNotAlreadyInUserRewards(attraction,userRewards)
                                        )
                                        .forEach(attraction ->
                                                userService.addUserRewardToUser(new UserReward(visitedLocation, attraction, rewardService.getAttractionsRewardPoint(attraction,user)),user)
                                        )),executor);
    }

    private boolean isAttractionNotAlreadyInUserRewards(Attraction attraction, List<UserReward> userRewards) {
        LOGGER.info("Cheking if attraction " +attraction +"  is already in user rewards "+ userRewards);
        return userRewards
                .stream()
                .noneMatch(userReward ->
                        userReward.getAttraction().getAttractionName().equals(attraction.getAttractionName()));
    }

    @Override
    public void addShutDownHook() {
        LOGGER.info("Reward Executer is shutting down");
        try{
            if(!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
            }catch(InterruptedException e){
                e.printStackTrace();
                executor.shutdownNow();
            } finally {
            Thread.currentThread().interrupt();
        }

    }
}
