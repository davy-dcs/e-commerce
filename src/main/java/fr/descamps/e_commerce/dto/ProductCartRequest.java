package fr.descamps.e_commerce.dto;


public record ProductCartRequest(
        String productReference,
        Double quantity
) {
}
