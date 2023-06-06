package fr.greta.UserService.controller;

import fr.greta.UserService.service.UserService;
import fr.greta.UserService.user.User;
import fr.greta.UserService.user.UserPreferences;
import gpsUtil.location.VisitedLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/{username}")
    public User getUserProfile(@PathVariable String username){
        LOGGER.info("getting user : " + username);

        User user = userService.getUser(username);
        return  user;
    }


    @GetMapping
    public List<User> getAllUsers() {
        LOGGER.info("getting all users");
        return userService.getAllUsers();
    }



    @GetMapping("/{username}/preferences")
    public UserPreferences getUserPreferences(@PathVariable final String username){
        LOGGER.info("getting user preferences for user : "+ username);
        return  userService.getUser(username).getUserPreferences();
    }

    @PutMapping("/{username}/preferences")
    public UserPreferences updateUserPreferences(@PathVariable final String username,
                                                 @RequestBody final UserPreferences updateUserPreferences){
      LOGGER.info("Updating user preferences for user : "+ username + " with preferences "+ updateUserPreferences);

      User user = userService.getUser(username);
      user.setUserPreferences(updateUserPreferences);
      userService.updateUser(user);
      return  updateUserPreferences;
    }



    @GetMapping("all-current-locations")
    public List<VisitedLocation> getAllCurrentLocations() {
        LOGGER.info("getting all user current locations ");
        return  userService.getAllUserCurrentLocations();
    }


}
