package org.globant.restaurant.service.Client;


import org.globant.restaurant.model.ClientDto;


public interface IClientService {

    ClientDto save(ClientDto client);

    void updateByDocument(ClientDto clientDto);

    void deleteByDocument(String document);

    ClientDto findClientByDocument(String document);
}
