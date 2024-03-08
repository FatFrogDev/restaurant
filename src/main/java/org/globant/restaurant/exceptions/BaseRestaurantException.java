package org.globant.restaurant.exceptions;

public abstract class BaseRestaurantException extends RuntimeException{
    public BaseRestaurantException(String message) {
        super(message);
    }
    public BaseRestaurantException(String message, Throwable cause) {
        super(message, cause);
    }
}
