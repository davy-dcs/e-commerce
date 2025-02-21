package fr.descamps.e_commerce.service;

import fr.descamps.e_commerce.domain.Cart;
import fr.descamps.e_commerce.domain.User;
import fr.descamps.e_commerce.dto.CartRequest;
import fr.descamps.e_commerce.dto.CartResponse;
import fr.descamps.e_commerce.dto.ReceiptResponse;

public interface ICartService {
    CartResponse updateStatus(CartRequest cartRequest);
    CartResponse getPendingOrNew(User user);
    Cart getPending(User user);
    ReceiptResponse getReceipt(User user);
}
