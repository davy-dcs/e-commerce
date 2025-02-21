package fr.descamps.e_commerce.service.impl;

import fr.descamps.e_commerce.domain.Cart;
import fr.descamps.e_commerce.domain.Product;
import fr.descamps.e_commerce.domain.ProductCart;
import fr.descamps.e_commerce.domain.User;
import fr.descamps.e_commerce.dto.ProductCartQuantityRequest;
import fr.descamps.e_commerce.dto.ProductCartRequest;
import fr.descamps.e_commerce.dto.ProductCartResponse;
import fr.descamps.e_commerce.dto.ProductCartUuidRequest;
import fr.descamps.e_commerce.dto.mapper.IProductCartMapper;
import fr.descamps.e_commerce.exception.ProductCartNotFoundException;
import fr.descamps.e_commerce.repository.IProductCartRepository;
import fr.descamps.e_commerce.service.ICartService;
import fr.descamps.e_commerce.service.IProductCartService;
import fr.descamps.e_commerce.service.IProductService;
import fr.descamps.e_commerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductCartServiceImpl implements IProductCartService {
    private final IProductCartRepository productCartRepository;
    private final ICartService cartService;
    private final IProductService productService;
    private final IProductCartMapper productCartMapper = IProductCartMapper.INSTANCE;

    @Override
    public ProductCartResponse create(User user, ProductCartRequest productCartRequest) {
        Product p = productService.getByRef(productCartRequest.productReference());
        Cart c = cartService.getPending(user);
        return productCartMapper.productToProductCartResponse(
                productCartRepository.save(
                        ProductCart.builder()
                                .product(p)
                                .cart(c)
                                .quantity(productCartRequest.quantity())
                                .build()
                )
        );
    }

    @Override
    public ProductCartResponse updateQuantity(ProductCartQuantityRequest productCartQuantityRequest) {
        ProductCart productCart = productCartRepository.findByUuid(productCartQuantityRequest.uuid()).orElseThrow(() -> new ProductCartNotFoundException("ProductCart not found by uuid"));
        if (productCartQuantityRequest.quantity() > 0) {
            productCartMapper.updateProductCart(productCartQuantityRequest, productCart);
            return productCartMapper.productToProductCartResponse(productCart);
        }
        deleteProduct(new ProductCartUuidRequest(productCartQuantityRequest.uuid()));
        return null;
    }

    @Override
    public void deleteProduct(ProductCartUuidRequest productCartUuidRequest) {
        ProductCart productCart = productCartRepository.findByUuid(productCartUuidRequest.uuid()).orElseThrow(() -> new ProductCartNotFoundException("ProductCart not found by uuid"));
        productCartRepository.delete(productCart);
    }
}
