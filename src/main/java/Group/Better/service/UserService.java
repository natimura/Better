package Group.Better.service;

import Group.Better.entity.User;

import java.util.Optional;

public interface UserService {
    void insertUser(String username, String password);

    Optional<User> findByUsernameLogin(String username);

    Optional<Long> findIdByUsername(String username);

    Optional<User> findByUsername(String username);

    void signUp(String username, String password);
}
