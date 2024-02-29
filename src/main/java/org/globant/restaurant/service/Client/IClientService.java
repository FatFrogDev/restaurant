package org.globant.restaurant.service.Client;

import org.globant.restaurant.entity.ClientEntity;

public interface IClientService {

    String updateClientByDocument(String document);

    String deleteClientByDocument(String document);

    ClientEntity findClientByDocument(String document);
}
