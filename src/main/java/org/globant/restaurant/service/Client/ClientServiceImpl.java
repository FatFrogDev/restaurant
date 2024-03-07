package org.globant.restaurant.service.Client;

import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.exceptions.EntityAlreadyExistsException;
import org.globant.restaurant.exceptions.EntityNotFoundException;
import org.globant.restaurant.mapper.ClientConverter;
import org.globant.restaurant.model.ClientDto;
import org.globant.restaurant.repository.Client.IClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {
    IClientRepository clientRepository;

    ClientConverter converter;

    public ClientServiceImpl(IClientRepository clientRepository, ClientConverter converter) {
        this.clientRepository = clientRepository;
        this.converter = converter;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        Optional<ClientEntity> client = clientRepository.findByDocument(clientDto.getDocument());
        ClientEntity clientEntity = converter.convertClientDtoToClientEntity(clientDto);
        if (!client.isPresent()){
            //TODO: validate client data doesnt have errors and then save.
            clientRepository.save(clientEntity);
            return clientDto;
        } throw new EntityAlreadyExistsException("Client already exists");
    }

    @Override
    public void updateByDocument(ClientDto clientDto) {
        System.out.println("clientDTO: " + clientDto);
        Optional<ClientEntity> optionalClient = clientRepository.findByDocument(clientDto.getDocument());
        System.out.println(optionalClient);
        if (optionalClient.isPresent()) {
            ClientEntity clientEntity = converter.convertClientDtoToClientEntity(clientDto);
            clientEntity.setUuid(optionalClient.get().getUuid());
            //TODO: validate client data doesnt have errors and then update.
            clientRepository.save(clientEntity);
        } else throw new EntityNotFoundException("Client does not exists");
    }

    @Override
    public void deleteByDocument(String document) {
        Optional<ClientEntity> optionalClient = clientRepository.findByDocument(document);
        if (optionalClient.isPresent()) {
            clientRepository.deleteByDocument(document);
        } else throw new EntityNotFoundException("Client to delete exists");
    }

    @Override
    public ClientDto findClientByDocument(String document) {
        Optional<ClientEntity> optionalClient = clientRepository.findByDocument(document);
        if (optionalClient.isPresent()) {
            return converter.convertClientEntityToClientDTO(optionalClient.get());
        }else throw new EntityNotFoundException("Client does not exists");
    }
}
