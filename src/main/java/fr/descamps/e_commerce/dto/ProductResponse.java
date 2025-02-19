package fr.descamps.e_commerce.dto;

import fr.descamps.e_commerce.domain.ProductPaymentTerms;
import fr.descamps.e_commerce.domain.ProductType;

public record ProductResponse(
        String reference,
        String name,
        Double price,
        ProductPaymentTerms paymentTerms,
        String image,
        ProductType type
) {
}
