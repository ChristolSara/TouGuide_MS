package fr.greta.TrackerService.service;

import fr.greta.TrackerService.models.src.main.java.org.openclassrooms.tourguide.models.model.location.Attraction;
import fr.greta.TrackerService.models.src.main.java.org.openclassrooms.tourguide.models.model.location.VisitedLocation;

import java.util.List;
import java.util.UUID;

public interface LocationService {

    VisitedLocation getUserLocation(final UUID userId);
    List<Attraction> getAllAttraction();
    List<Attraction> getAttractionNearVisitedLocation(VisitedLocation visitedLocation);
}
