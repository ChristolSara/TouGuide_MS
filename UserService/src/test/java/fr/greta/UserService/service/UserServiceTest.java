package fr.greta.UserService.service;

import fr.greta.UserService.exception.ElementNotFound;
import fr.greta.UserService.repository.UserRepository;
import fr.greta.UserService.user.User;
import fr.greta.UserService.user.UserPreferences;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.*;

import java.time.Instant;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {


    @Mock
    private static UserRepository userRepository;
    private static UserService userService;

    private static User user;
    private static final UUID uuid1=UUID.randomUUID();
    private static List<User> allUsers = new ArrayList<>();

    @BeforeEach
    private void beforEach(){
        userService=new UserServiceImpl(userRepository);
        user = new User(uuid1,"userName","phoneNumber","emailAddress",
                Date.from(Instant.now()),new ArrayList<>(),new ArrayList<>(),new UserPreferences(),new ArrayList<>());
        allUsers.add(user);
    }
//get all users
    @Test
    void getAllUsersTest(){
        when(userRepository.findAll()).thenReturn(allUsers);
        userService.getAllUsers();
        verify(userRepository,times(1)).findAll();

    }
//get user by name

    @Test
    void getUserWithExistingIdTest(){
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        userService.getUser(user.getUsername());
        verify(userRepository,times(1)).findByUsername(user.getUsername());
    }

    @Test
    void getUserWithNonExistentIdTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(ElementNotFound.class, () -> userService.getUser(user.getUsername()));
        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }

    //add user if not exist
    @Test
    void addNonExistentUserTest(){
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);
        userService.addUser(user);
        verify(userRepository,times(1)).existsByUsername(user.getUsername());
        verify(userRepository,times(1)).save(user);
    }

    @Test
    void updateExistingUserTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        userService.updateUser(user);
        verify(userRepository, times(1)).save(user);
    }
    @Test
    void updateNonExistentUserTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(ElementNotFound.class, () -> userService.updateUser(user));
    }

    //location test

    @Test
    void getExistingUserCurrentLocationWithNotEmptyLocationTest(){
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        userService.getUserCurrentLocation(user.getUsername());
        verify(userRepository,times(1)).findByUsername(user.getUsername());
    }

    @Test
    void getExistingUserCurrentLocationWithNotEmtpyLocationTest(){
        List<VisitedLocation> visitedLocations = new ArrayList<>();
        visitedLocations.add(new VisitedLocation(user.getUserId(),new Location(2d,2d),Date.from(Instant.now())));
        user.setVisitedLocations(visitedLocations);
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        userService.getUserCurrentLocation(user.getUsername());
        verify(userRepository,times(1)).findByUsername(user.getUsername());
    }
    @Test
    void getNonExistentUserCurrentLocationTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(ElementNotFound.class, () -> userService.getUserCurrentLocation(user.getUsername()));
        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }

    @Test
    void getAllUserCurrentLocations() {
        when(userRepository.findAll()).thenReturn(allUsers);
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        userService.getAllUserCurrentLocations();
        verify(userRepository, times(1)).findAll();
    }
}
