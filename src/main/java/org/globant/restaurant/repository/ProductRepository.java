package org.globant.restaurant.repository;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    public String createProduct(String uuid){
        return "Product " + uuid + " created successfully!";
    }

    public String readProductByUUID() {
        return  "Product found successfully!";
    }
}
