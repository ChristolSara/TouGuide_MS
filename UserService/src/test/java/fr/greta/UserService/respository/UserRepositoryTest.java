package fr.greta.UserService.respository;

import fr.greta.UserService.data.InternalDataTest;
import fr.greta.UserService.repository.UserRepository;
import fr.greta.UserService.repository.UserRepositoryTestImpl;
import fr.greta.UserService.user.User;
import fr.greta.UserService.user.UserPreferences;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SuppressWarnings("OptionalGetWithoutIsPresent")
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    private UserRepository userRepository;
    private static User user;
    private static  final UUID uuid1 = UUID.randomUUID();

    @BeforeEach
    void beforEach(){
        InternalDataTest.setInterUserNumber(99);
        userRepository = new UserRepositoryTestImpl();
        user = new User(uuid1,"userName","phoneNumber","emailAdress",
                Date.from(Instant.now()),new ArrayList<>(),new ArrayList<>(),new UserPreferences(),new ArrayList<>());
    }

    @Test
    void findAllTest(){
        assertEquals(99,userRepository.findAll().size());
    }

    @Test
    void findNonExistentUserByUserNameTest(){
        Optional<User> retrievedUser = userRepository.findByUsername(user.getUsername()) ;
        assertEquals(Optional.empty(),retrievedUser);
    }

    @Test
    void existsByUsernameTrueTest(){
        userRepository.save(user);
        assertTrue(userRepository.existsByUsername(user.getUsername()));
    }
    @Test
    void saveExistingUserTest(){
        userRepository.save(user);
        user.setEmailAddress("emailTest");
        userRepository.save(user);
        Optional<User> retrievedUser = userRepository.findByUsername(user.getUsername());
        assertEquals("emailTest",retrievedUser.get().getEmailAddress());
    }

    @Test
    void saveNonExistinUserTest(){
        userRepository.save(user);
        Optional<User> retrievedUser = userRepository.findByUsername(user.getUsername());
        assertEquals(Optional.of(user),retrievedUser);
    }
}
