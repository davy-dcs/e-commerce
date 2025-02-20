package fr.descamps.e_commerce.dto;

public record AuthRequest(
        String username,
        String password
) {
}
