package org.globant.restaurant.exceptions.Client;

/**
 * The type Client data invalid or incomplete exception.
 */
public class ClientDataInvalidOrIncompleteException extends Error{
    /**
     * Instantiates a new Client data invalid or incomplete exception.
     *
     * @param error the error
     */
    public ClientDataInvalidOrIncompleteException(String error) {
        super(error);
    }
}
