package fr.greta.GpsService.model;


import fr.greta.GpsService.model.location.Attraction;
import fr.greta.GpsService.model.location.Location;
import fr.greta.GpsService.model.location.VisitedLocation;

public class FromLibraryToModelConvertor {

    public static Attraction convertAttraction(final gpsUtil.location.Attraction libraryAttraction) {
        return new Attraction(libraryAttraction.attractionName,
                libraryAttraction.city,
                libraryAttraction.state,
                libraryAttraction.latitude,
                libraryAttraction.longitude);
    }

    public static VisitedLocation convertVisitedLocation(final gpsUtil.location.VisitedLocation libraryVisitedLocation) {
        return new VisitedLocation(libraryVisitedLocation.userId,
                new Location(libraryVisitedLocation.location.latitude, libraryVisitedLocation.location.longitude),
                libraryVisitedLocation.timeVisited);
    }
}
