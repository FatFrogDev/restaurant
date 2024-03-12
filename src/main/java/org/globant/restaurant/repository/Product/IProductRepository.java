
package org.globant.restaurant.repository.Product;

import org.globant.restaurant.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByUuid(UUID uuid);

    Optional<ProductEntity> findByFantasyName(String fantasyName);

    void deleteByUuid(UUID uuid);
    // Custom query to find product by fantasy name by using JPA
    List<ProductEntity> findAllByFantasyNameContaining(String productName);

    // Custom query to find product by fantasy name by using JPQL
    @Query("SELECT p FROM ProductEntity p WHERE p.fantasyName LIKE  %?1%")
    List<ProductEntity> findAllByFantasyNameContainingJPQL(String productName);

    // Custom query to find product by fantasy name by using raw SQL
    @Query(value = "SELECT * FROM products WHERE fantasy_name LIKE %:product_name%" , nativeQuery = true)
    List<ProductEntity> findAllByFantasyNameContainingRawSQL(@Param("product_name") String productName);
}
