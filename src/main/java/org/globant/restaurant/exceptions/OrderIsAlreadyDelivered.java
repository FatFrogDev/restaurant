package org.globant.restaurant.exceptions;

public class OrderIsAlreadyDelivered extends BaseRestaurantException{
    public OrderIsAlreadyDelivered(String message) {
        super(message);
    }
}
