package fr.descamps.e_commerce.service.impl;

import fr.descamps.e_commerce.domain.Cart;
import fr.descamps.e_commerce.domain.CartStatus;
import fr.descamps.e_commerce.domain.ProductCart;
import fr.descamps.e_commerce.domain.User;
import fr.descamps.e_commerce.dto.CartRequest;
import fr.descamps.e_commerce.dto.CartResponse;
import fr.descamps.e_commerce.dto.ProductCartResponse;
import fr.descamps.e_commerce.dto.ReceiptResponse;
import fr.descamps.e_commerce.dto.mapper.ICartMapper;
import fr.descamps.e_commerce.dto.mapper.IProductCartMapper;
import fr.descamps.e_commerce.exception.CartNotFoundException;
import fr.descamps.e_commerce.repository.ICartRepository;
import fr.descamps.e_commerce.service.ICartService;
import fr.descamps.e_commerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements ICartService {
    private final ICartRepository cartRepository;
    private final UserService userService;
    private final IProductCartMapper productCartMapper = IProductCartMapper.INSTANCE;
    private final ICartMapper cartMapper = ICartMapper.INSTANCE;

    @Override
    public CartResponse updateStatus(CartRequest cartRequest) {
        Cart cart = cartRepository.findByUuid(cartRequest.uuid()).orElseThrow(() -> new CartNotFoundException("Cart not found by uuid"));
        cartMapper.updateCart(cartRequest, cart);
        Cart cartSaved = cartRepository.save(cart);
        return cartMapper.cartToCartResponse(cartSaved);
    }

    @Override
    public CartResponse getPendingOrNew(User user) {
        User u = userService.getByUsername(user.getUsername());
        Optional<Cart> cart = cartRepository.findByUser_UsernameAndStatus(u.getUsername(), CartStatus.PENDING);
        if (cart.isPresent()) {
            return cartMapper.cartToCartResponse(cart.get());
        }
        Cart newCart = cartRepository.save(Cart.builder()
                .user(u)
                .status(CartStatus.PENDING)
                .build()
        );
        return cartMapper.cartToCartResponse(newCart);
    }

    @Override
    public Cart getPending(User user) {
        User u = userService.getByUsername(user.getUsername());
        return cartRepository.findByUser_UsernameAndStatus(u.getUsername(), CartStatus.PENDING).orElseThrow(() -> new CartNotFoundException("Cart not found by username and status"));
    }

    @Override
    public ReceiptResponse getReceipt(User user) {
        Cart cart = getPending(user);

        List<ProductCartResponse> productCartResponses = new ArrayList<>();
        double totalPrice = 0.0;
        for (ProductCart productCart : cart.getProductCarts()){
            productCartResponses.add(productCartMapper.productToProductCartResponse(productCart));
            totalPrice += productCart.getProduct().getPrice() * productCart.getQuantity();
        }

        return new ReceiptResponse(productCartResponses, totalPrice);
    }
}
