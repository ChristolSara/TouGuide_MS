package fr.greta.TrackerService.executer;


import fr.greta.TrackerService.models.user.User;

import java.util.concurrent.CompletableFuture;

public interface RewardExecutor {

    CompletableFuture<?> calculateRewards(User user);
    void addShutDownHook();
}
