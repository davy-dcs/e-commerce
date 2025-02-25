package fr.descamps.e_commerce.service;

import fr.descamps.e_commerce.domain.Product;
import fr.descamps.e_commerce.domain.ProductType;
import fr.descamps.e_commerce.dto.ProductResponse;
import fr.descamps.e_commerce.dto.ProductTypeRequest;

import java.util.List;

public interface IProductService {
    List<ProductResponse> getAll(ProductTypeRequest productType);
    ProductResponse getByReference(String reference);
    Product getByRef(String reference);
    List<ProductType> getTypes();
}
