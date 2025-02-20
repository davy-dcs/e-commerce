package fr.descamps.e_commerce.repository;

import fr.descamps.e_commerce.domain.Cart;
import fr.descamps.e_commerce.domain.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUuid(UUID uuid);
    Optional<Cart> findByUser_UsernameAndStatus(String username, CartStatus status);
}
