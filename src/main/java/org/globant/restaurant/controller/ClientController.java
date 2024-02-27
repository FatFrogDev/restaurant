package org.globant.restaurant.controller;

import org.globant.restaurant.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {


    @Autowired
    ClientServiceImpl clientService;
    @PutMapping("/clients/{document}")
    public String updateClient(@PathVariable String document){
        return clientService.updateClientByDocument(document);
    }

    @DeleteMapping("/clients/{document}")
    public String deleteClient(@PathVariable String document){
        return clientService.deleteClientByDocument(document);
    }
}
