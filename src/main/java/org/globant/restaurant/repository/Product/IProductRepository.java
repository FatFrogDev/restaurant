package org.globant.restaurant.repository.Product;

import org.globant.restaurant.model.ProductDTO;
import java.util.List;
import java.util.UUID;

public interface IProductRepository {
    String saveProduct(String uuid);

    String findProductByUUID();

    ProductDTO getProductRepository(UUID idProduct);

    List<ProductDTO> getAllProductService();

    ProductDTO update(ProductDTO productDTO);
}
