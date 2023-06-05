package fr.greta.TripService.service;

import fr.greta.TripService.model.FromLibreryToModelConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tripPricer.Provider;
import tripPricer.TripPricer;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService{


    private static  final Logger LOGGER = LoggerFactory.getLogger(TripServiceImpl.class);

    private final TripPricer tripPricer = new TripPricer();

    public TripServiceImpl(){}

    private static final String tripPricerApiKey = "test_server_api_key";
    @Override
    public List<Provider> getTripDeal(UUID userId, int numberOfAdults, int numberOfChildren, int tripDuration, int cumulativeRewardPoints) {
        LOGGER.info("getting trip deals for user : "+userId);

        return tripPricer.getPrice(tripPricerApiKey,
                userId,numberOfAdults,numberOfChildren,tripDuration,cumulativeRewardPoints)
                .stream()
                .map(FromLibreryToModelConvertor::convertProvider)
                .collect(Collectors.toList());
    }
}
