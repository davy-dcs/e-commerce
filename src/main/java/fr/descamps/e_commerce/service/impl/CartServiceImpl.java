package fr.descamps.e_commerce.service.impl;

import fr.descamps.e_commerce.domain.Cart;
import fr.descamps.e_commerce.dto.CartRequest;
import fr.descamps.e_commerce.dto.CartResponse;
import fr.descamps.e_commerce.dto.mapper.ICartMapper;
import fr.descamps.e_commerce.exception.CartNotFoundException;
import fr.descamps.e_commerce.repository.ICartRepository;
import fr.descamps.e_commerce.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
