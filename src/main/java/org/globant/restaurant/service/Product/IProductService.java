package org.globant.restaurant.service.Product;

import org.globant.restaurant.model.ClientDto;
import org.globant.restaurant.model.ProductDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductService {
     ResponseEntity<?> save(ProductDTO productDTO);

     Optional<?> findByUUID(ProductDTO productDTO);

     ProductDTO getProductService(UUID idProduct);

     List<ProductDTO> getAllProductService();

     ProductDTO updateProduct(UUID idProduct, ProductDTO productDTO) throws Exception;
}
