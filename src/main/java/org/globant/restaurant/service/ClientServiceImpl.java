package org.globant.restaurant.service;

import org.globant.restaurant.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements IClientService{

    @Autowired
    ClientRepository clientRepository;

    @Override
    public String updateClientByDocument(String document) {
        return clientRepository.updateClientByDocument(document);
    }

    @Override
    public String deleteClientByDocument(String document) {
        return  clientRepository.deleteClientByDocument(document);
    }
}
