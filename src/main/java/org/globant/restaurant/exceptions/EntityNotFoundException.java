package org.globant.restaurant.exceptions;

public class EntityNotFoundException extends BaseRestaurantException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
