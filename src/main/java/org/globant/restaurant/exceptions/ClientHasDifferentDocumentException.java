package org.globant.restaurant.exceptions;

public class ClientHasDifferentDocumentException extends ClientInvalidDocumentFormatException{
    public ClientHasDifferentDocumentException(String message) {
        super(message);
    }
}
