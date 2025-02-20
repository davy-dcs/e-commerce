package fr.descamps.e_commerce.controller;

import fr.descamps.e_commerce.domain.User;
import fr.descamps.e_commerce.dto.AuthRequest;
import fr.descamps.e_commerce.dto.AuthResponse;
import fr.descamps.e_commerce.dto.RegisterRequest;
import fr.descamps.e_commerce.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) throws RoleNotFoundException {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }
}
