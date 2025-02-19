package fr.descamps.e_commerce.service;

import fr.descamps.e_commerce.domain.ProductType;
import fr.descamps.e_commerce.dto.ProductResponse;

import java.util.List;

public interface IProductService {
    List<ProductResponse> getAll(ProductType productType);
    ProductResponse getByReference(String reference);
}
