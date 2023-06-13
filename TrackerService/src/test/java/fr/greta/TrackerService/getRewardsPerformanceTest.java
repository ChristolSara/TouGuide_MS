package fr.greta.TrackerService;

import fr.greta.TrackerService.config.WebClientConfig;
import fr.greta.TrackerService.executer.RewardExecutor;
import fr.greta.TrackerService.executer.RewardExecutorImpl;
import fr.greta.TrackerService.models.location.Attraction;
import fr.greta.TrackerService.models.location.VisitedLocation;
import fr.greta.TrackerService.models.user.User;
import fr.greta.TrackerService.service.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
public class getRewardsPerformanceTest {


    private UserService userService;
    private LocationService locationService;
    private RewardService rewardService;
    private RewardExecutor rewardExecutor;
    private static WebClient webClientUserApi;
    private static WebClient webClientGpsApi;
    private static WebClient webClientRewardApi;
    private static WebClientConfig webClientConfig;

    private List<User> allUsers;
    private StopWatch stopWatch;

    @BeforeAll
    public static void beforeAll() {
        Locale.setDefault(Locale.US);
        webClientConfig = new WebClientConfig();
        webClientUserApi = webClientConfig.getWebClientUserApi();
        webClientGpsApi = webClientConfig.getWebClientGpsApi();
        webClientRewardApi = webClientConfig.getWebClientRewardApi();
    }

    @BeforeEach
    public void beforEach(){
        allUsers = new ArrayList<>();
        stopWatch = new StopWatch();
        userService = new UserServiceImpl(webClientUserApi);
        locationService = new LocationServiceImpl(webClientGpsApi);
        rewardService = new RewardServiceImpl(webClientRewardApi);
        rewardExecutor = new RewardExecutorImpl(locationService,rewardService,userService);
    }


    @AfterEach
    public void afterEach(){
        rewardExecutor.addShutDownHook();
        System.out.println("\n get reward with "+ allUsers.size()+"users.Time Elapsed:  "+
                TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " second. \n");
        stopWatch.reset();
    }

    @Test
    void getUserRewardsPerformance(){
        Attraction attraction = locationService.getAllAttraction().get(0);
        allUsers = userService.getAllUsers();
        allUsers.forEach(user -> userService.addToVisitedLocationsOfUser(new VisitedLocation(user.getUserId(),
                attraction, Date.from(Instant.now())),user));

        stopWatch.start();
        CompletableFuture<?>[] completableFutures = allUsers.stream()
                .map(rewardExecutor::calculateRewards)
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(completableFutures).join();

        stopWatch.stop();
        assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
    }


}
