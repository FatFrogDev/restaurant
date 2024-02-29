package org.globant.restaurant.service.Client;

import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.repository.Client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientServiceImpl implements IClientService {

    ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public String updateClientByDocument(String document) {
        return clientRepository.updateClientByDocument(document);
    }

    @Override
    public String deleteClientByDocument(String document) {
        return  clientRepository.deleteClientByDocument(document);
    }

    @Override
    public ClientEntity findClientByDocument(String document) {
        return clientRepository.findClientByDocument(document);
    }


}
