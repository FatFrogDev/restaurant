package org.globant.restaurant.exceptions;


public class GenericServerError extends BaseRestaurantException {
    public GenericServerError(String message, Throwable cause) {
        super(message, cause);
    }
}
