package org.globant.restaurant.repository.Product;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository implements IProductRepository{
    @Override
    public String saveProduct(String uuid){
        return "Product " + uuid + " created successfully!";
    }

    @Override
    public String findProductByUUID() {
        return  "Product found successfully!";
    }
}
