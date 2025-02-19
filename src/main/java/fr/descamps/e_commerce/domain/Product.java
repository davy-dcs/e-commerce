package fr.descamps.e_commerce.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;

    private String name;

    private Double price;

    private String image;

    @Enumerated(EnumType.STRING)
    private ProductPaymentTerms PaymentTerms;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(id, product.id) && Objects.equals(reference, product.reference) && Objects.equals(name, product.name) && type == product.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, name, type);
    }
}
