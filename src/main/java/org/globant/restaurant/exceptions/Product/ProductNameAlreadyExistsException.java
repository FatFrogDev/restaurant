package org.globant.restaurant.exceptions.Product;

public class ProductNameAlreadyExistsException extends Error{
    public ProductNameAlreadyExistsException(String message) {
        super(message);
    }
}
