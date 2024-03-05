package org.globant.restaurant.exceptions.Product;

public class ProductNotFoundException extends Error{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
