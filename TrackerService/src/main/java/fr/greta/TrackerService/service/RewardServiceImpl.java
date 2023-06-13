package fr.greta.TrackerService.service;

import fr.greta.TrackerService.models.location.Attraction;
import fr.greta.TrackerService.models.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RewardServiceImpl implements RewardService {


    private static final Logger LOGGER = LoggerFactory.getLogger(RewardServiceImpl.class);
    private final WebClient webClientRewardApi;

    public RewardServiceImpl(@Qualifier("getWebClientRewardApi")final WebClient webClientRewardApi1) {
     webClientRewardApi = webClientRewardApi1;
    }

    @Override
    public int getAttractionsRewardPoint(final Attraction attraction, final User user) {
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
