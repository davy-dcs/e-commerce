package fr.descamps.e_commerce.service;

import fr.descamps.e_commerce.domain.User;
import fr.descamps.e_commerce.dto.ProductCartQuantityRequest;
import fr.descamps.e_commerce.dto.ProductCartRequest;
import fr.descamps.e_commerce.dto.ProductCartResponse;

import java.util.UUID;

public interface IProductCartService {
    ProductCartResponse create(User user, ProductCartRequest productCartRequest);
    void updateQuantity(ProductCartQuantityRequest productCartQuantityRequest);
    void deleteProduct(UUID uuid);
}
