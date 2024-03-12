package org.globant.restaurant.controller;

import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.model.ClientDto;
import org.globant.restaurant.service.Client.ClientServiceImpl;
import org.globant.restaurant.service.Client.IClientService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    IClientService clientService;

    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{document}")
    public ResponseEntity<ClientDto> findClientByDocument(@PathVariable String document){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findClientByDocument(document));
    }

    @PostMapping("")
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientDto));
    }


    @PutMapping("/{document}")
    public ResponseEntity updateClient(@PathVariable String document, @RequestBody ClientDto clientdto){
        clientService.updateByDocument(clientdto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{document}")
    public ResponseEntity deleteClient(@PathVariable String document){
        clientService.deleteByDocument(document);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
