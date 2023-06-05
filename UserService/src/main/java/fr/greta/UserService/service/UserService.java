package fr.greta.UserService.service;

import fr.greta.UserService.user.User;
import gpsUtil.location.VisitedLocation;

import java.util.List;

public interface UserService {


    List<User> getAllUsers();

    User getUser(String username);
     User addUser(User user);
     User updateUser(User updateUser);

     VisitedLocation getUserCurrentLocation(String username);

     List<VisitedLocation> getAllUserCurrentLocations();

}
