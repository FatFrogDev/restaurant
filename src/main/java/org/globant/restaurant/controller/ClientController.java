package org.globant.restaurant.controller;

import org.globant.restaurant.mapper.ClientConverter;
import org.globant.restaurant.model.ClientDto;
import org.globant.restaurant.service.Client.ClientServiceImpl;
import org.globant.restaurant.service.Client.IClientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    IClientService clientService;
    ClientConverter converter;

    public ClientController(ClientServiceImpl clientService, ClientConverter converter) {
        this.clientService = clientService;
        this.converter = converter;
    }

    @GetMapping("/{document}")
    public ClientDto findClientByDocument(@PathVariable String document){
        return converter.convertClientEntityToClientDTO(
                clientService.findClientByDocument(document));
    }

    @PostMapping("/create/{uuid}")
        public String createClient(@PathVariable String uuid){return clientService.createClient(uuid);}

    @PutMapping("/update/{document}")
    public String updateClient(@PathVariable String document){
        return clientService.updateClientByDocument(document);
    }

    @DeleteMapping("/{document}")
    public String deleteClient(@PathVariable String document){
        return clientService.deleteClientByDocument(document);
    }

}
