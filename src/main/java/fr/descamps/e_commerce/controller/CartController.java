package fr.descamps.e_commerce.controller;

import fr.descamps.e_commerce.domain.User;
import fr.descamps.e_commerce.dto.CartRequest;
import fr.descamps.e_commerce.dto.CartResponse;
import fr.descamps.e_commerce.service.ICartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private final ICartService cartService;

    @GetMapping
    public ResponseEntity<CartResponse> getPending(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(cartService.getPendingOrNew(user));
    }

    @PutMapping
    public ResponseEntity<CartResponse> updateStatus(@RequestBody CartRequest cartRequest) {
        return ResponseEntity.ok(cartService.updateStatus(cartRequest));
    }
}
