package fr.descamps.e_commerce.dto;

import java.util.UUID;

public record ProductCartQuantityRequest(
        UUID uuid,
        Double quantity
) {
}
