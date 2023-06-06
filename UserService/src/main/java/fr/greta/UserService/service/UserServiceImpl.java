package fr.greta.UserService.service;

import fr.greta.UserService.exception.ElementExisting;
import fr.greta.UserService.exception.ElementNotFound;
import fr.greta.UserService.repository.UserRepository;
import fr.greta.UserService.user.User;
import gpsUtil.location.VisitedLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{


    private static  final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        LOGGER.info("Getting all users");
        return userRepository.findAll();
    }

    @Override
    public User getUser(String username) {
        LOGGER.info("getting user : " + username);
        return userRepository.findByUsername(username)
                .orElseThrow(() ->new ElementNotFound("no user for username : "+username)) ;
    }

    @Override
    public User addUser(User user) {
        LOGGER.info("Adding user : "+ user);
        if(userRepository.existsByUsername(user.getUsername())){
            throw  new ElementExisting("user "+user.getUsername()+" is already existing");
        }else {
            return userRepository.save(user);
        }

    }
    @Override
    public User updateUser(User updateUser) {
        LOGGER.info("updating user : "+ updateUser );
        getUser(updateUser.getUsername());
        return userRepository.save(updateUser);
    }

    @Override
    public VisitedLocation getUserCurrentLocation(final String username) {
        LOGGER.info("getting user current location for user " + username);
        List<VisitedLocation> userVisitedlocation = getUser(username).getVisitedLocations();
        if(userVisitedlocation.isEmpty()){
            return null;
        }else{
            return userVisitedlocation.get(userVisitedlocation.size() -1);
        }

    }

    @Override
    public List<VisitedLocation> getAllUserCurrentLocations() {

        LOGGER.info("getting all users current locations. ");
        List<User> allUsers = getAllUsers();
        List<VisitedLocation> allCurrentLocations = new ArrayList<>();

        for(User user :allUsers){
            VisitedLocation currentLocation = getUserCurrentLocation(user.getUsername());
            allCurrentLocations.add(currentLocation);
        }
        return allCurrentLocations;
    }
}
