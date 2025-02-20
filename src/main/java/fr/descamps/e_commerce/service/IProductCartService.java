package fr.descamps.e_commerce.service;

import fr.descamps.e_commerce.dto.ProductCartRequest;
import fr.descamps.e_commerce.dto.ProductCartResponse;
import fr.descamps.e_commerce.dto.ProductCartUuidRequest;

public interface IProductCartService {
    ProductCartResponse updateQuantity(ProductCartRequest productCartRequest);
    void deleteProduct(ProductCartUuidRequest productCartUuidRequest);
}
