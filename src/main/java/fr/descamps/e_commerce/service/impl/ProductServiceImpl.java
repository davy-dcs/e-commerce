package fr.descamps.e_commerce.service.impl;

import fr.descamps.e_commerce.domain.ProductType;
import fr.descamps.e_commerce.dto.ProductResponse;
import fr.descamps.e_commerce.dto.mapper.IProductMapper;
import fr.descamps.e_commerce.exception.ProductNotFoundException;
import fr.descamps.e_commerce.repository.IProductRepository;
import fr.descamps.e_commerce.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;

    @Override
    public List<ProductResponse> getAll(ProductType productType) {
        return (productType == null) ?
            IProductMapper.INSTANCE.productsToProductResponseList(productRepository.findAll()) :
                IProductMapper.INSTANCE.productsToProductResponseList(productRepository.findByType(productType));
    }

    @Override
    public ProductResponse getByReference(String reference) {
        return productRepository.findByReference(reference)
                .map(IProductMapper.INSTANCE::productToProductResponse)
                .orElseThrow(() -> new ProductNotFoundException("Product not found by reference"));
    }
}
