package fr.greta.UserService.repository;

import fr.greta.UserService.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
public class UserRepositoryTestImpl implements UserRepository{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTestImpl.class);

    private final Map<String,User> internalUserMap;

    public UserRepositoryTestImpl(Map<String, User> internalUserMap) {
        this.internalUserMap = internalUserMap;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }

    @Override
    public User save(User user) {
        return null;
    }
}
