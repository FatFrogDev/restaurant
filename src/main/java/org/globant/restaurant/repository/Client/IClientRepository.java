package org.globant.restaurant.repository.Client;

import org.globant.restaurant.entity.ClientEntity;

public interface IClientRepository {
     String updateClientByDocument(String document);

    ClientEntity findClientByDocument(String document);
}