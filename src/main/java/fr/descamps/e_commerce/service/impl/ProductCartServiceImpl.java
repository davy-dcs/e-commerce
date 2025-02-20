package fr.descamps.e_commerce.service.impl;

import fr.descamps.e_commerce.domain.ProductCart;
import fr.descamps.e_commerce.dto.ProductCartRequest;
import fr.descamps.e_commerce.dto.ProductCartResponse;
import fr.descamps.e_commerce.dto.ProductCartUuidRequest;
import fr.descamps.e_commerce.dto.mapper.IProductCartMapper;
import fr.descamps.e_commerce.exception.ProductCartNotFoundException;
import fr.descamps.e_commerce.repository.IProductCartRepository;
import fr.descamps.e_commerce.service.IProductCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductCartServiceImpl implements IProductCartService {
    private final IProductCartRepository productCartRepository;
    private final IProductCartMapper productCartMapper = IProductCartMapper.INSTANCE;

    @Override
    public ProductCartResponse updateQuantity(ProductCartRequest productCartRequest) {
        ProductCart productCart = productCartRepository.findByUuid(productCartRequest.uuid()).orElseThrow(() -> new ProductCartNotFoundException("ProductCart not found by uuid"));
        productCartMapper.updateProductCart(productCartRequest, productCart);
        return productCartMapper.productToProductCartResponse(productCart);
    }

    @Override
    public void deleteProduct(ProductCartUuidRequest productCartUuidRequest) {
        ProductCart productCart = productCartRepository.findByUuid(productCartUuidRequest.uuid()).orElseThrow(() -> new ProductCartNotFoundException("ProductCart not found by uuid"));
        productCartRepository.delete(productCart);
    }
}
