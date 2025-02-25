package fr.descamps.e_commerce.controller;

import fr.descamps.e_commerce.domain.User;
import fr.descamps.e_commerce.dto.ProductCartQuantityRequest;
import fr.descamps.e_commerce.dto.ProductCartRequest;
import fr.descamps.e_commerce.dto.ProductCartResponse;
import fr.descamps.e_commerce.service.IProductCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/product-cart")
@RequiredArgsConstructor
public class ProductCartController {
    private final IProductCartService productCartService;

    @PostMapping
    public ResponseEntity<ProductCartResponse> create(@AuthenticationPrincipal User user, @RequestBody ProductCartRequest productCartRequest) {
        return ResponseEntity.ok(productCartService.create(user, productCartRequest));
    }

    @PutMapping
    public ResponseEntity<Void> updateQuantity(@RequestBody ProductCartQuantityRequest productCartQuantityRequest) {
        productCartService.updateQuantity(productCartQuantityRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID uuid) {
        productCartService.deleteProduct(uuid);
        return ResponseEntity.ok().build();
    }

}
