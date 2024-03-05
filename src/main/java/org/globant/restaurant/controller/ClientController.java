package org.globant.restaurant.controller;

import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.mapper.ClientConverter;
import org.globant.restaurant.model.ClientDto;
import org.globant.restaurant.service.Client.ClientServiceImpl;
import org.globant.restaurant.service.Client.IClientService;
<<<<<<< HEAD
import org.springframework.http.ResponseEntity;
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> main
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    IClientService clientService;

    @GetMapping("/{document}")
<<<<<<< HEAD
    public ResponseEntity<?> findClientByDocument(@PathVariable String document){
        return clientService.findClientByDocument(document);
    }

    @PostMapping("")
        public ResponseEntity<?> createClient(@RequestBody ClientDto clientDto){
        return clientService.save(clientDto);
=======
    public ClientEntity findClientByDocument(@PathVariable String document){
        return this.clientService.findClientByDocument(document);
    }

    @PostMapping("/create")
        public String createClient(@RequestBody ClientDto clientDto){
        return clientService.createClient(clientDto);
        }

    @PutMapping("/update/{document}")
    public String updateClient(@PathVariable String document){
        return clientService.updateClientByDocument(document);
>>>>>>> main
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
