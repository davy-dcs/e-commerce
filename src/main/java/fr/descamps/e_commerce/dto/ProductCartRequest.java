package fr.descamps.e_commerce.dto;

import java.util.UUID;

public record ProductCartRequest(
        UUID uuid,
        Integer quantity
) {
}
