package org.globant.restaurant.service.Product;

import org.globant.restaurant.model.ClientDto;
import org.globant.restaurant.model.ProductDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductService {
     ProductDTO save(ProductDTO productDTO);

     ProductDTO findByUuid(UUID uuid);

     void updateByUuid(UUID uuid, ProductDTO productDTO);

     void deleteByUuid(UUID uuid);
}