package fr.greta.GpsService.service;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;

import java.util.List;
import java.util.UUID;

public interface GpsService {

    Attraction getAttraction(String attractionName);
    List<Attraction> getAllAttractions();
    VisitedLocation getUserActualLocation(final UUID userId);
}
