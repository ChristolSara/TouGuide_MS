package fr.greta.UserService.controller;
import fr.greta.UserService.exception.ElementExisting;
import fr.greta.UserService.exception.ElementNotFound;

import fr.greta.UserService.service.UserService;
import fr.greta.UserService.user.User;
import fr.greta.UserService.user.UserPreferences;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(UserControllerTest.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserService userService;
    private static User user;
    private static UserPreferences userPreferences;
    private static final UUID uuid1 = UUID.randomUUID();
    private static VisitedLocation visitedLocation;
    private static List<VisitedLocation> visitedLocationList;
    private static List<User> userList;



    @BeforeAll
    static void beforeAll(){
        user = new User(uuid1, "userName", "phoneNumber", "emailAddress",  Date.from(Instant.now()), new ArrayList<>(), new ArrayList<>(), new UserPreferences(), new ArrayList<>());
        userPreferences = new UserPreferences();
        visitedLocation = new VisitedLocation(UUID.randomUUID(), new Location(2d, 2d), Date.from(Instant.now()));
        visitedLocationList = new ArrayList<>();
        visitedLocationList.add(visitedLocation);
        userList = new ArrayList<>();
        userList.add(user);
    }

    @Test
    void getAllCurrentLocations() throws Exception  {
        when(userService.getAllUserCurrentLocations()).thenReturn(visitedLocationList);
        mockMvc.perform(get("/users/all-current-locations"))
                .andExpect(status().isOk());
    }

    @Test
    void getExistingUserLocation() throws Exception {
        when(userService.getUserCurrentLocation(anyString())).thenReturn(visitedLocation);
        mockMvc.perform(get("/users/{username}/current-location", user.getUsername()))
                .andExpect(status().isOk());
    }

    @Test
    void getNonExistentUserLocation() throws Exception {
        when(userService.getUserCurrentLocation(anyString())).thenThrow(ElementNotFound.class);
        mockMvc.perform(get("/users/{username}/current-location", user.getUsername()))
                .andExpect(status().isNotFound());
    }

    @Test
    void getExistingUserRewards() throws Exception {
        when(userService.getUser(anyString())).thenReturn(user);
        mockMvc.perform(get("/users/{username}/rewards", user.getUsername()))
                .andExpect(status().isOk());
    }

    @Test
    void getNonExistentUserRewards() throws Exception {
        when(userService.getUser(anyString())).thenThrow(ElementNotFound.class);
        mockMvc.perform(get("/users/{username}/rewards", user.getUsername()))
                .andExpect(status().isNotFound());
    }

}
