package org.globant.restaurant.service.Client;

import org.globant.restaurant.model.ClientDto;
import org.springframework.http.ResponseEntity;

public interface IClientService {

    ResponseEntity<?> save(ClientDto client);

    ResponseEntity<?> updateByDocument(ClientDto clientDto);

    ResponseEntity<?> deleteClientByDocument(String document);

    ResponseEntity findClientByDocument(String document);
}
