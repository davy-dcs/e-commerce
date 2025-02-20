package fr.descamps.e_commerce.controller;

import fr.descamps.e_commerce.dto.ProductCartRequest;
import fr.descamps.e_commerce.dto.ProductCartResponse;
import fr.descamps.e_commerce.dto.ProductCartUuidRequest;
import fr.descamps.e_commerce.service.IProductCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product-cart")
@RequiredArgsConstructor
public class ProductCartController {
    private final IProductCartService productCartService;

    @PutMapping
    public ResponseEntity<ProductCartResponse> updateQuantity(@RequestBody ProductCartRequest productCartRequest) {
        return ResponseEntity.ok(productCartService.updateQuantity(productCartRequest));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteProduct(@RequestBody ProductCartUuidRequest productCartUuidRequest) {
        productCartService.deleteProduct(productCartUuidRequest);
        return ResponseEntity.ok().build();
    }

}
