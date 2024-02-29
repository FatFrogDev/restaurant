package org.globant.restaurant.exceptions;

public class UserAlreadyExistException extends Error{
    public UserAlreadyExistException(String message, Exception cause){
        super(message, cause);
        System.out.println("Whoops, there was an error: " + message + " , the cause was: " + cause);
    };
}
