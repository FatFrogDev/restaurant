package org.globant.restaurant.commons.constans.response.client;

public interface IClientResponse {
    String CLIENT_EXIST = "Client already exists";
    String CLIENT_NOT_EXIST = "Client does not exists";
    String CLIENT_DIFFERENT_DATA = "Client has no different data";
    String CLIENT_INVALID_ARGUMENTS = "Invalid arguments provided order must be 'asc' or 'desc' & field must be 'name', 'document' or 'address'";
    String CLIENT_NOT_CHANGES = "The client has no changes to be updated.";
    String CLIENT_HAS_CHANGES_IN_DOCUMENT = "The document of the client cannot be updated.";
    String CLIENT_INVALID_FORMAT_DOCUMENT= "The document has an incorrect format.";
}