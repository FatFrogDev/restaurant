package org.globant.restaurant.service.Product;

import org.globant.restaurant.repository.ProductRepository;
import org.globant.restaurant.service.Product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public String createProduct(String uuid) {
        return productRepository.createProduct(uuid);
    }

    @Override
    public String readProductByUUID() {
        return "Productos: ";
    }
}
