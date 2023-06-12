package fr.greta.UserService.repository;

import fr.greta.UserService.data.InternalTestInitializer;
import fr.greta.UserService.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
public class UserRepositoryTestImpl implements UserRepository{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTestImpl.class);

    private final Map<String,User> internalUserMap ;

    public UserRepositoryTestImpl() {
        LOGGER.info("TestMode enabled");
        LOGGER.debug("Initializing users");
        internalUserMap = new InternalTestInitializer().initializeInternalUsers();
        LOGGER.debug("Finished initializing users");
    }

    @Override
    public List<User> findAll() {
        LOGGER.info("Finding All user : ");
        return new ArrayList<>(internalUserMap.values());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        LOGGER.info("Finding user : "+ username);
        User user = internalUserMap.get(username);
        return Optional.ofNullable(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        LOGGER.info("checking if user : "+ username +" exist. ");
        return internalUserMap.containsKey(username);
    }

    @Override
    public User save(User user) {
        LOGGER.info("Saving user : " + user );
        return internalUserMap.containsKey(user.getUsername()) ?
                internalUserMap.replace(user.getUsername(),user):
                internalUserMap.put(user.getUsername(),user);
    }
}
