package org.globant.restaurant.repository;

public class ProductRepository {
    public String createProduct(String uuid){
        return "Product " + uuid + " created successfully!";
    }

    public String readProductByUUID(String uuid) {
        return  "Product " + uuid + " found successfully!";
    }
}
