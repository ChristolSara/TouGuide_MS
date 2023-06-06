package fr.greta.UserService.data;

import fr.greta.UserService.user.User;
import fr.greta.UserService.user.UserPreferences;
import fr.greta.UserService.user.UserReward;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import jakarta.annotation.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import tripPricer.Provider;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.IntStream;

@Profile("test")
public class InternalTestInitializer {


    private static  final Logger LOGGER = LoggerFactory.getLogger(InternalTestInitializer.class);

    private  Map<String,User> internalUserMap;
    public Map<String , User> initializeInternalUsers() {


        internalUserMap = new HashMap<>();
        IntStream.range(0, InternalDataTest.getInterUserNumber()).forEach(i -> {
            String userName = "internalUser" + i;
            String phone = "0000";
            String email = userName + "@tourGuide.comp";
            Date date = Date.from(Instant.now());
            List<VisitedLocation> visitedLocations = new ArrayList<>();
            List<UserReward> userRewards = new ArrayList<>();
            UserPreferences userPreferences = new UserPreferences();
            List<Provider> providerList = new ArrayList<>();
            User user = new User(UUID.randomUUID(), userName, phone, email, date, visitedLocations, userRewards, userPreferences, providerList);
            generateUserLocationHistory(user);
            internalUserMap.put(userName, user);
        });

        LOGGER.debug("created"  + InternalDataTest.getInterUserNumber() +" internal test  users. ");
        return  internalUserMap;
    }



        private void generateUserLocationHistory(User user) {
            IntStream.range(0, 3).forEach(i-> addVisitedLocation(user, new VisitedLocation(user.getUserId(), new Location(generateRandomLatitude(), generateRandomLongitude()), getRandomTime())));
        }



        private double generateRandomLatitude(){
        double leftLimit = -85.05112878;
        double rightLimit = 85.05112878;
        return  leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
        }


        private double generateRandomLongitude(){
        double leftLimit = -180;
        double rightLimit = 180;
        return  leftLimit +new Random().nextDouble() * (rightLimit - leftLimit);
        }


        private Date getRandomTime(){
            LocalDateTime localDateTime = LocalDateTime.now().minusDays(new Random().nextInt(30));
            return (Date) Date.from(localDateTime.toInstant(ZoneOffset.UTC));
        }

        private void addVisitedLocation(User user, VisitedLocation visitedLocation){
        List<VisitedLocation> visitedLocations = user.getVisitedLocations();
        visitedLocations.add(visitedLocation);
        }


}
