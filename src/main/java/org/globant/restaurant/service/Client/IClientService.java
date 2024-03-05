package org.globant.restaurant.service.Client;

<<<<<<< HEAD
import org.globant.restaurant.model.ClientDto;
import org.springframework.http.ResponseEntity;
=======
import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.model.ClientDto;
>>>>>>> main

public interface IClientService {

    ResponseEntity<?> save(ClientDto client);

    ResponseEntity<?> updateByDocument(ClientDto clientDto);

    ResponseEntity<?> deleteByDocument(String document);

<<<<<<< HEAD
    ResponseEntity<?> findClientByDocument(String document);
=======
    String createClient(ClientDto clientDto);
>>>>>>> main
}
