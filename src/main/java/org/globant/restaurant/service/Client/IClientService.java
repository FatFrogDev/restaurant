package org.globant.restaurant.service.Client;

import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.model.ClientDto;

public interface IClientService {

    String updateClientByDocument(String document);

    String deleteClientByDocument(String document);

    ClientEntity findClientByDocument(String document);

    String createClient(ClientDto clientDto);
}
