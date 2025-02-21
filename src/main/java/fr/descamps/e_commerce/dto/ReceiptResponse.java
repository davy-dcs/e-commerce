package fr.descamps.e_commerce.dto;

import java.util.List;

public record ReceiptResponse(
        List<ProductCartResponse> productCarts,
        Double totalPrice
) {
}
