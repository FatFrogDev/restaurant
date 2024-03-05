package org.globant.restaurant.exceptions.Order;

public class OrderDataInvalidOrIncompleteException extends Error{
    public OrderDataInvalidOrIncompleteException(String message) {
        super(message);
    }
}
