package fr.descamps.e_commerce.repository;

import fr.descamps.e_commerce.domain.Product;
import fr.descamps.e_commerce.domain.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByType(ProductType type);
    Optional<Product> findByReference(String reference);
}
