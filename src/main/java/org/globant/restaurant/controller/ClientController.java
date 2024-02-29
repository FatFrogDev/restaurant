package org.globant.restaurant.controller;

import org.globant.restaurant.service.Client.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientServiceImpl clientService;

    @PostMapping("/create/{uuid}")
    public String createClient(@PathVariable String uuid){return clientService.createClient(uuid);}
    @GetMapping("/find/{document}")
    public String findClientByDocument(@PathVariable String document){return clientService.findClientByDocument(document);}
    @PutMapping("/update/{document}")
    public String updateClient(@PathVariable String document){
        return clientService.updateClientByDocument(document);
    }

    @DeleteMapping("/delete/{document}")
    public String deleteClient(@PathVariable String document){
        return clientService.deleteClientByDocument(document);
    }
}
