package Group.Better.service.impl;

import Group.Better.entity.User;
import Group.Better.exception.UsernameAlreadyExistsException;
import Group.Better.repository.UserRepository;
import Group.Better.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private void checkUsernameExists(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException("入力されたユーザー名はすでに登録されています。別のユーザー名を入力してください。");
        }
    }

    @Override
    public void insertUser(String username, String password) {
        checkUsernameExists(username);
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsernameLogin(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<Long> findIdByUsername(String username) {
        return userRepository.findByUsername(username).map(User::getId);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void signUp(String username, String password){
        insertUser(username, password);
    }
}