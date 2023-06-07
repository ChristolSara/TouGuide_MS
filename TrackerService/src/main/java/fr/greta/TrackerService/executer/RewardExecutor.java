package fr.greta.TrackerService.executer;

import fr.greta.TrackerService.models.src.main.java.org.openclassrooms.tourguide.models.model.user.User;

import java.util.concurrent.CompletableFuture;

public interface RewardExecutor {

    CompletableFuture<?> calculateRewards(User user);
    void addShutDownHook();
}
