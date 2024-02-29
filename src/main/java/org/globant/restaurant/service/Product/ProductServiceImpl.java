package org.globant.restaurant.service.Product;

import org.globant.restaurant.repository.Product.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String save(String uuid) {
        return productRepository.saveProduct(uuid);
    }

    @Override
    public String findByUUID() {
        return "Productos: ";
    }
}
