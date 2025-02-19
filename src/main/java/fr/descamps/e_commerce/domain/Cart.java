package fr.descamps.e_commerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private CartStatus status;

    @OneToMany
    private List<ProductCart> products;

    @PrePersist
    private void prePersist() {
        this.uuid = UUID.randomUUID();
        this.date = LocalDate.now();
    }
}
