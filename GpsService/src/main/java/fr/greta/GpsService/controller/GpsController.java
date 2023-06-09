package fr.greta.GpsService.controller;
//
//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;
import fr.greta.GpsService.model.location.Attraction;
import fr.greta.GpsService.model.location.VisitedLocation;
import fr.greta.GpsService.service.GpsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class GpsController {

   private static  final Logger LOGGER = LoggerFactory.getLogger(GpsController.class);

   private final GpsService gpsService;

    public GpsController(final GpsService gpsService1) {
        gpsService = gpsService1;

    }

    @GetMapping("/attractions/{attractionName}")
    public fr.greta.GpsService.model.location.Attraction getAttractionInformation(@PathVariable final String attractionName) {
        LOGGER.info("getting attraction "+ attractionName);
        return gpsService.getAttraction(attractionName);
    }

    @GetMapping("/attractions")
    public List<Attraction> getAllAttractions(){
        LOGGER.info("getting all attractions");
        return  gpsService.getAllAttractions();
    }

    @GetMapping("/location/{userId}")
    public VisitedLocation getUserLocation(@PathVariable final UUID userId){
        LOGGER.info("getting location for user : "+ userId);
        return gpsService.getUserActualLocation(userId);
    }
}
