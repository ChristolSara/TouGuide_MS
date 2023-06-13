package fr.greta.TrackerService.executer;


import fr.greta.TrackerService.models.user.User;

import java.util.concurrent.CompletableFuture;

public interface TrackerExecutor {

    CompletableFuture<?> trackUserLocation(User user);

    void addShutDownHook();
}
