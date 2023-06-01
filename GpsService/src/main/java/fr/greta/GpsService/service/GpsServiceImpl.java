package fr.greta.GpsService.service;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import fr.greta.GpsService.exception.ElementNotFoundException;
import fr.greta.GpsService.model.FromLibraryToModelConvertor;
import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GpsServiceImpl implements GpsService{
    private  static  final Logger LOGGER = LoggerFactory.getLogger(GpsServiceImpl.class);
    private final GpsUtil gpsUtil;


//constructeur avc parametres
    public GpsServiceImpl(GpsUtil gpsUtil) {
        this.gpsUtil = gpsUtil;
        Locale.setDefault(Locale.US);
    }

    @Override
    public Attraction getAttraction(final String attractionName) {

       // LOGGER.info("search for attraction with name " + attractionName);
        return FromLibraryToModelConvertor.convertAttraction(gpsUtil.getAttractions().stream()
                .filter(attraction -> attractionName.equals(attraction.attractionName))
                .findAny()
                .orElseThrow(() -> new ElementNotFoundException("attraction with name :"+ attractionName + " was not found")));
    }

    @Override
    public List<Attraction> getAllAttractions() {
       // LOGGER.info("Getting all referenced attraction");
        return gpsUtil.getAttractions()
                .stream()
                .map(FromLibraryToModelConvertor::convertAttraction)
                .collect(Collectors.toList());
    }

    @Override
    public VisitedLocation getUserActualLocation(final UUID userId) {
        //LOGGER.info("Getting user "+ userId + " actual location with gps .");
        return FromLibraryToModelConvertor.convertVisitedLocation(gpsUtil.getUserLocation(userId));
    }
}
