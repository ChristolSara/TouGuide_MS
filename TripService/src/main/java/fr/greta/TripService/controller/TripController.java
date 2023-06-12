package fr.greta.TripService.controller;

import fr.greta.TripService.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tripPricer.Provider;

import java.util.List;
import java.util.UUID;

@RestController
public class TripController {


    private static final Logger LOGGER = LoggerFactory.getLogger(TripController.class);

    private TripService tripService;


    public TripController(TripService tripService) {
        this.tripService = tripService;
    }


    @GetMapping("/trips/{username}")
    public List<Provider> getTripDeals(
            @PathVariable final String username,
            @PathVariable final UUID userId,
            @PathVariable final int numberOfAdults,
            @PathVariable final int numberOfChildren,
            @PathVariable final int tripDuration,
            @PathVariable final int cumulativeRewardPoints){

        LOGGER.info("Getting trips deals for user "+ username);

        return  tripService.getTripDeals(userId,numberOfAdults,numberOfChildren,tripDuration,cumulativeRewardPoints);
    }
}
