package fr.descamps.e_commerce.repository;

import fr.descamps.e_commerce.domain.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IProductCartRepository extends JpaRepository<ProductCart, Long> {
    Optional<ProductCart> findByUuid(UUID uuid);

    Optional<ProductCart> findByProduct_ReferenceAndCart_Uuid(String reference, UUID uuid);
}
