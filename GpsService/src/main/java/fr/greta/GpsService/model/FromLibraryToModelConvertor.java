package fr.greta.GpsService.model;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

public class FromLibraryToModelConvertor {


    public static Attraction convertAttraction(final gpsUtil.location.Attraction libraryAttraction){
        return  new Attraction(libraryAttraction.attractionName,
                libraryAttraction.city,
                libraryAttraction.state,
                libraryAttraction.latitude,
                libraryAttraction.longitude);
    }

    public  static VisitedLocation convertVisitedLocation(final gpsUtil.location.VisitedLocation libreryVisitedLocation){
        return new VisitedLocation(libreryVisitedLocation.userId,
                new Location(libreryVisitedLocation.location.latitude,
                        libreryVisitedLocation.location.longitude),
                libreryVisitedLocation.timeVisited);
    }

}
