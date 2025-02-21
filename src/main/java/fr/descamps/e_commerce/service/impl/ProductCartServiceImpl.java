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

import java.util.List;

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

        if (containProduct(c.getProductCarts(), p)) {
            ProductCart productCart = productCartRepository.findByProduct_ReferenceAndCart_Uuid(productCartRequest.productReference(), c.getUuid()).orElseThrow(() -> new ProductCartNotFoundException("Product Cart not found by product reference and cart uuid"));
            productCart.setQuantity(productCart.getQuantity() + productCartRequest.quantity());
            return productCartMapper.productToProductCartResponse(productCartRepository.save(productCart));
        } else {
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
    }

    private Boolean containProduct(List<ProductCart> productCarts, Product product) {
        for (ProductCart productCart : productCarts) {
            if (productCart.getProduct().equals(product)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ProductCartResponse updateQuantity(ProductCartQuantityRequest productCartQuantityRequest) {
        ProductCart productCart = productCartRepository.findByUuid(productCartQuantityRequest.uuid()).orElseThrow(() -> new ProductCartNotFoundException("ProductCart not found by uuid"));
        if (productCartQuantityRequest.quantity() > 0) {
            productCartMapper.updateProductCart(productCartQuantityRequest, productCart);
            return productCartMapper.productToProductCartResponse(productCart);
        } else {
            deleteProduct(new ProductCartUuidRequest(productCartQuantityRequest.uuid()));
            return null;
        }
    }

    @Override
    public void deleteProduct(ProductCartUuidRequest productCartUuidRequest) {
        ProductCart productCart = productCartRepository.findByUuid(productCartUuidRequest.uuid()).orElseThrow(() -> new ProductCartNotFoundException("ProductCart not found by uuid"));
        productCartRepository.delete(productCart);
    }
}
