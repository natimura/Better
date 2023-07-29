package Group.Better.impl;

import Group.Better.details.CustomUserDetails;
import Group.Better.repository.UserRepository;
import Group.Better.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new CustomUserDetails(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.emptyList(),
                        user.getId()
                ))
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Given username is not found. (username = '" + username + "')"
                ));
    }
}
