package org.globant.restaurant.controller;

import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.mapper.ClientConverter;
import org.globant.restaurant.model.ClientDto;
import org.globant.restaurant.service.Client.ClientServiceImpl;
import org.globant.restaurant.service.Client.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    IClientService clientService;

    @GetMapping("/{document}")
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
    }

    @DeleteMapping("/{document}")
    public String deleteClient(@PathVariable String document){
        return clientService.deleteClientByDocument(document);
    }

}
