package fr.greta.RewardService.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rewardCentral.RewardCentral;


import java.util.UUID;
@Service
public class RewardServieImpl implements  RewardService{


    private static final Logger LOGGER = LoggerFactory.getLogger(RewardServieImpl.class);
    private  final RewardCentral rewardCentral;

    public RewardServieImpl(final RewardCentral rewardCentral) {
        this.rewardCentral = rewardCentral;
    }

    @Override
    public int getRewardPoints(UUID attractionId, UUID userId) {

        LOGGER.info("Getting rewards points for attraction " + attractionId+ " and user  "+userId);
        return rewardCentral.getAttractionRewardPoints(attractionId,userId);
    }
}
