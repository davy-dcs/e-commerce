package fr.descamps.e_commerce.service;

import fr.descamps.e_commerce.domain.Role;
import fr.descamps.e_commerce.domain.User;
import fr.descamps.e_commerce.dto.AuthRequest;
import fr.descamps.e_commerce.dto.AuthResponse;
import fr.descamps.e_commerce.dto.RegisterRequest;
import fr.descamps.e_commerce.repository.IRoleRepository;
import fr.descamps.e_commerce.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    public User register(RegisterRequest registerRequest) throws RoleNotFoundException {
        Role role = roleRepository.findByName("USER").orElseThrow(() -> new RoleNotFoundException("Role not found"));

        return userRepository.save(
                User.builder()
                    .username(registerRequest.username())
                    .password(passwordEncoder.encode(registerRequest.password()))
                    .role(role)
                    .build()
        );
    }

    public AuthResponse authenticate(AuthRequest authRequest) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
        );
        return new AuthResponse(auth.getName());
    }
}
