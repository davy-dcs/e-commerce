package fr.descamps.e_commerce.dto;

import java.util.UUID;

public record ProductCartResponse(
        UUID uuid,
        ProductResponse product,
        Double quantity
) {
}
