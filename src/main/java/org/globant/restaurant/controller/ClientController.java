package org.globant.restaurant.controller;

import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.model.ClientDto;
import org.globant.restaurant.service.Client.ClientServiceImpl;
import org.globant.restaurant.service.Client.IClientService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    IClientService clientService;

    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{document}")
    public ResponseEntity<?> findClientByDocument(@PathVariable String document){
        return clientService.findClientByDocument(document);
    }

    @PostMapping("")
    public ResponseEntity<?> createClient(@RequestBody ClientDto clientDto){
        return clientService.save(clientDto);
    }


    @PutMapping("/{document}")
    public ResponseEntity<?> updateClient(@PathVariable String document, @RequestBody ClientDto clientdto){
        clientService.updateByDocument(clientdto);
        return ResponseEntity
                .ok()
                .body(clientService.findClientByDocument(document));
    }

    @DeleteMapping("/{document}")
    public void deleteClient(@PathVariable String document){
         clientService.deleteByDocument(document);
    }

}
