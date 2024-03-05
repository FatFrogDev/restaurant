package org.globant.restaurant.service.Product;

import org.globant.restaurant.model.ProductDTO;

import java.util.List;
import java.util.UUID;

public interface IProductService {
     String save(String uuid);

     String findByUUID();

     ProductDTO getProductService(UUID idProduct);

     List<ProductDTO> getAllProductService();

     ProductDTO updateProduct(UUID idProduct, ProductDTO productDTO) throws Exception;
}
