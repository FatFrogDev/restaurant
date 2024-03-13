package org.globant.restaurant.service.Product;

import org.globant.restaurant.model.ProductDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface IProductService {
     ProductDTO save(ProductDTO productDTO);

    ProductDTO findByUuid(String uuid);

    @Transactional
    void updateByUuid(String uuid, ProductDTO productDTO);

    @Transactional
     void deleteByUuid(String uuid);

    ProductDTO findByFantasyName(String fantasyName);

    boolean existsByUuid(String uuid);
}