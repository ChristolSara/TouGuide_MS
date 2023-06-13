package fr.greta.TrackerService;

import fr.greta.TrackerService.config.WebClientConfig;
import fr.greta.TrackerService.executer.RewardExecutor;
import fr.greta.TrackerService.executer.RewardExecutorImpl;
import fr.greta.TrackerService.executer.TrackerExecutor;
import fr.greta.TrackerService.executer.TrackerExecutorImpl;
import fr.greta.TrackerService.models.user.User;
import fr.greta.TrackerService.service.*;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
public class TrackUserLocationPerformanceTest {

    private UserService userService;
    private LocationService locationService;
    private RewardService rewardService;
    private TrackerExecutor trackerExecutor;
    private RewardExecutor rewardExecutor;
    private static WebClient webClientUserApi;
    private static  WebClient webClientGpsApi;
    private static WebClient webClientRewardApi;
    private  static WebClientConfig webClientConfig;

    private List<User> allUsers;
    private StopWatch stopWatch;



    @BeforeAll
    public static void beforeAll(){
        Locale.setDefault(Locale.US);
        webClientConfig = new WebClientConfig();
        webClientRewardApi = webClientConfig.getWebClientRewardApi();
        webClientUserApi = webClientConfig.getWebClientUserApi();
        webClientGpsApi = webClientConfig.getWebClientGpsApi();

    }
    @BeforeEach
    public void beforeEach(){
        allUsers = new ArrayList<>();
        stopWatch = new StopWatch();
        userService = new UserServiceImpl(webClientUserApi);
        locationService = new LocationServiceImpl(webClientGpsApi);
        rewardService = new RewardServiceImpl(webClientRewardApi);
        rewardExecutor = new RewardExecutorImpl(locationService,userService,rewardService);
        trackerExecutor = new TrackerExecutorImpl(locationService,userService,rewardExecutor);
    }

    @AfterEach
    public void afterEach(){
        trackerExecutor.addShutDownHook();
        System.out.println("\n Track location with "+allUsers.size() + " users time Elpased "+
                TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime())+" seconds.\n");
        stopWatch.reset();
    }

    @Test
    void trackUserLocationPerformance(){
        allUsers = userService.getAllUsers();
        stopWatch.start();

        CompletableFuture<?>[] completableFutures = allUsers.stream()
                .map(trackerExecutor::trackUserLocation)
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(completableFutures)
                .join();

        stopWatch.stop();

        assertTrue(TimeUnit.SECONDS.toSeconds(9) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
    }
}
