package fr.greta.UserService.repository;

import fr.greta.UserService.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {


    List<User> findAll();


    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    User save(User user);
}
