package org.globant.restaurant.exceptions;

public class EntityAlreadyExistsException extends BaseRestaurantException{

    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
