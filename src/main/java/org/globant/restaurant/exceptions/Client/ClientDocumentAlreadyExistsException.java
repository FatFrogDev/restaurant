package org.globant.restaurant.exceptions.Client;

public class ClientDocumentAlreadyExistsException extends Error{
    public ClientDocumentAlreadyExistsException(String message){
        super(message);
    };
}
