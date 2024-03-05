package org.globant.restaurant.service.Client;

import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.mapper.ClientConverter;
import org.globant.restaurant.model.ClientDto;
<<<<<<< HEAD
import org.globant.restaurant.repository.Client.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
=======
import org.globant.restaurant.repository.Client.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
>>>>>>> main


@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    IClientRepository clientRepository;
    @Autowired
    ClientConverter clientConverter;

<<<<<<< HEAD
    ClientConverter converter;

    public ClientServiceImpl(ClientRepository clientRepository, ClientConverter converter) {
        this.clientRepository = clientRepository;
        this.converter = converter;
    }

    @Override
    public ResponseEntity<?> save(ClientDto clientDto) {
        ClientEntity clientEntity = converter.convertClientDTOToClientEntity(clientDto);
        System.out.println(clientEntity.toString());
        try {
            clientRepository.save(clientEntity);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(clientEntity);
        } catch (Exception e){
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateByDocument(ClientDto clientDto) {
        Optional<ClientEntity> OptionalClient = clientRepository.findByDocument(clientDto.getDocument());

        if (OptionalClient.isPresent()) {
            ClientEntity clientEntity = converter.convertClientDTOToClientEntity(clientDto);
            clientEntity.setUuid(OptionalClient.get().getUuid());
            clientRepository.save(clientEntity);
            return ResponseEntity.ok().body(clientEntity);
        }
        return ResponseEntity.badRequest().body("Client with document not found");
    }

    @Override
    public ResponseEntity<?> deleteByDocument(String document) {
        Optional<ClientEntity> optionalClient = clientRepository.findByDocument(document);
        if (optionalClient.isPresent()) {
            clientRepository.deleteByDocument(document);
            return ResponseEntity.ok().body("Client deleted correctly");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
    }

    @Override
    public ResponseEntity<?> findClientByDocument(String document) {
        Optional<ClientEntity> optionalClient = clientRepository.findByDocument(document);
        if (optionalClient.isPresent()) {
            return ResponseEntity.ok().body(optionalClient.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
=======
    @Override
    public String createClient(ClientDto clientDto){
        try{
            Optional<ClientEntity> find = this.clientRepository.findById(clientDto.getUuid());
            if(find.isEmpty()) {
                ClientEntity clientEntity = this.clientConverter.convertClientDtoToClientEntity(clientDto);
                this.clientRepository.save(clientEntity);
                return "ok";
            }
            else{
                return "Bad";
            }
        }
        catch (Exception e)
        {
            return HttpStatus.INTERNAL_SERVER_ERROR + String.format("Exception", e);
        }
    }

    @Override
    public String updateClientByDocument(String document) {
        return "In process";
    }

    @Override
    public String deleteClientByDocument(String document) {
        return "In process";
    }

    @Override
    public ClientEntity findClientByDocument(String document) {
        try{
            Optional<ClientEntity> clientEntity = this.clientRepository.findClientByDocument(document);
            return clientEntity.orElse(null);
        }
        catch (Exception e){
            return null;
        }
>>>>>>> main
    }
}
