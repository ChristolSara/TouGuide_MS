package fr.greta.TripService.model;

import tripPricer.Provider;
import tripPricer.TripPricer;

public class FromLibreryToModelConvertor {



    public static Provider convertProvider(tripPricer.Provider libraryProvider){
        return  new Provider(libraryProvider.tripId, libraryProvider.name, libraryProvider.price);
    }
}
