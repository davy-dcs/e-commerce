package fr.descamps.e_commerce.dto;

import fr.descamps.e_commerce.domain.CartStatus;
import fr.descamps.e_commerce.domain.ProductCart;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record CartResponse(
        UUID uuid,
        LocalDate date,
        CartStatus status,
        List<ProductCart> products
) {
}
