package org.globant.restaurant.repository;

import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {

    public String updateClientByDocument(String document){
        return "Client " + document + " updated successfully!";
    }

    public String deleteClientByDocument(String document) {
        return  "Client " + document + " deleted successfully!";
    }
}
