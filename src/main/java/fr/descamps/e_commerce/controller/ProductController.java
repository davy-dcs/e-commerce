package fr.descamps.e_commerce.controller;

import fr.descamps.e_commerce.domain.ProductType;
import fr.descamps.e_commerce.dto.ProductResponse;
import fr.descamps.e_commerce.dto.ProductTypeRequest;
import fr.descamps.e_commerce.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll(@RequestBody ProductTypeRequest productType) {
        return ResponseEntity.ok(productService.getAll(productType));
    }

    @GetMapping("/{reference}")
    public ResponseEntity<ProductResponse> getByReference(@PathVariable String reference) {
        return ResponseEntity.ok(productService.getByReference(reference));
    }
}
