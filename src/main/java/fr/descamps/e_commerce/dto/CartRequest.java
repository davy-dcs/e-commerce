package fr.descamps.e_commerce.dto;

import fr.descamps.e_commerce.domain.CartStatus;

import java.util.UUID;

public record CartRequest(
        UUID uuid,
        CartStatus status
) {
}
