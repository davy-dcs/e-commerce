package fr.descamps.e_commerce.service.impl;

import fr.descamps.e_commerce.domain.Cart;
import fr.descamps.e_commerce.domain.CartStatus;
import fr.descamps.e_commerce.domain.User;
import fr.descamps.e_commerce.dto.CartRequest;
import fr.descamps.e_commerce.dto.CartResponse;
import fr.descamps.e_commerce.dto.mapper.ICartMapper;
import fr.descamps.e_commerce.exception.CartNotFoundException;
import fr.descamps.e_commerce.exception.UserNotFoundException;
import fr.descamps.e_commerce.repository.ICartRepository;
import fr.descamps.e_commerce.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements ICartService {
    private final ICartRepository cartRepository;
    private final ICartMapper cartMapper = ICartMapper.INSTANCE;

    @Override
    public CartResponse updateStatus(CartRequest cartRequest) {
        Cart cart = cartRepository.findByUuid(cartRequest.uuid()).orElseThrow(() -> new CartNotFoundException("Cart not found by uuid"));
        cartMapper.updateCart(cartRequest, cart);
        return cartMapper.cartToCartResponse(cart);
    }

    @Override
    public CartResponse getPendingOrNew(User user) {
        Optional<Cart> cart = cartRepository.findByUser_UsernameAndStatus(user.getUsername(), CartStatus.PENDING);
        if (cart.isPresent()) {
            return cartMapper.cartToCartResponse(cart.get());
        } else {
            Cart newCart = Cart.builder()
                    .user(user)
                    .status(CartStatus.PENDING)
                    .build();
            return cartMapper.cartToCartResponse(cartRepository.save(newCart));
        }
    }
}
