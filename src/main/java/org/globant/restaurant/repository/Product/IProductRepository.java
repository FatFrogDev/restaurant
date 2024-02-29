package org.globant.restaurant.repository.Product;

public interface IProductRepository {
    String saveProduct(String uuid);

    String findProductByUUID();
}
