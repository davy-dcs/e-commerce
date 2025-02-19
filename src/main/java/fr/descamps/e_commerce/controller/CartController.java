package fr.descamps.e_commerce.controller;

import fr.descamps.e_commerce.dto.CartRequest;
import fr.descamps.e_commerce.dto.CartResponse;
import fr.descamps.e_commerce.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private final ICartService cartService;

    @PutMapping
    public ResponseEntity<CartResponse> updateStatus(CartRequest cartRequest) {
        return ResponseEntity.ok(cartService.updateStatus(cartRequest));
    }
}
