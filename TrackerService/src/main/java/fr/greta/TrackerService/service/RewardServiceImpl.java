package fr.greta.TrackerService.service;

import fr.greta.TrackerService.models.src.main.java.org.openclassrooms.tourguide.models.model.location.Attraction;
import fr.greta.TrackerService.models.src.main.java.org.openclassrooms.tourguide.models.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.RequestEntity.get;

public class RewardServiceImpl implements RewardService {


    private static final Logger LOGGER = LoggerFactory.getLogger(RewardService.class);
    private final WebClient webClientRewardApi;

    public RewardServiceImpl(@Qualifier("getWebClientRewardApi")final WebClient webClientRewardApi1) {
        this.webClientRewardApi = webClientRewardApi1;
    }

    @Override
    public int getAttractionsRewardPoint(final Attraction attraction,final User user) {
        LOGGER.info("getting attraction reward point for attraction " + attraction.getAttractionName()+" and user "+user.getUsername());

        return webClientRewardApi
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/rewards")
                        .queryParam("attractionId",attraction.getAttractionId())
                        .queryParam("userId",user.getUserId())
                        .build())
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }
}
