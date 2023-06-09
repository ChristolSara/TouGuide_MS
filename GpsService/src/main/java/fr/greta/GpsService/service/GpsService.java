package fr.greta.GpsService.service;

import fr.greta.GpsService.model.location.Attraction;
import fr.greta.GpsService.model.location.VisitedLocation;

import java.util.List;
import java.util.UUID;

public interface GpsService {

   Attraction getAttraction(String attractionName);
    List<Attraction> getAllAttractions();
    VisitedLocation getUserActualLocation(final UUID userId);
}
