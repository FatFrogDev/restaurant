package org.globant.restaurant.exceptions;

public class InvalidQueryArgsException extends BaseRestaurantException{
    public InvalidQueryArgsException(String message) {
        super(message);
    }
}
