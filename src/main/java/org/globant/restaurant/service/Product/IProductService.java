package org.globant.restaurant.service.Product;

import org.globant.restaurant.model.ProductDTO;

import java.util.UUID;

public interface IProductService {
     ProductDTO save(ProductDTO productDTO);

    ProductDTO findByUuid(String uuid);

    void updateByUuid(String uuid, ProductDTO productDTO);

     void deleteByUuid(String uuid);

    ProductDTO findByFantasyName(String fantasyName);

    boolean existsByUuid(String uuid);
}