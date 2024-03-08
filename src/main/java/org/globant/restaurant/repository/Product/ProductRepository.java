package org.globant.restaurant.repository.Product;

import org.globant.restaurant.entity.ProductEntity;
import org.globant.restaurant.model.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
