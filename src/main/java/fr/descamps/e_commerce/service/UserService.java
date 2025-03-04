package fr.descamps.e_commerce.service;

import fr.descamps.e_commerce.domain.User;
import fr.descamps.e_commerce.exception.UserNotFoundException;
import fr.descamps.e_commerce.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final IUserRepository userRepository;

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found by username"));
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> User.builder()
                                .username(user.getUsername())
                                .password(user.getPassword())
                                .role(user.getRole())
                                .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));
    }
}
