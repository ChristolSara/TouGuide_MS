package fr.greta.UserService.service;

import fr.greta.UserService.user.User;
import gpsUtil.location.VisitedLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{


    private static  final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User updateUser) {
        return null;
    }

    @Override
    public VisitedLocation getUserCurrentLocation(String username) {
        return null;
    }

    @Override
    public List<VisitedLocation> getAllUserCurrentLocations() {
        return null;
    }
}
