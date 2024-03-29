package org.globant.restaurant.controller;


import lombok.AllArgsConstructor;
import org.globant.restaurant.commons.constans.endPoints.client.IClientEndPoint;
import org.globant.restaurant.model.ClientDto;
import org.globant.restaurant.service.Client.ClientServiceImpl;
import org.globant.restaurant.service.Client.IClientService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(IClientEndPoint.BASE_URL_CLIENTS)
public class ClientController {

    private final IClientService clientService;

    @GetMapping(IClientEndPoint.FIND_CLIENT_DOCUMENT)
    public ResponseEntity<ClientDto> findClientByDocument(@PathVariable String document){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findClientByDocument(document));
    }
    @PostMapping(IClientEndPoint.CREATE_CLIENT)
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientDto));
    }
    @PutMapping(IClientEndPoint.UPDATE_CLIENT)
    public ResponseEntity updateClient(@PathVariable String document, @RequestBody ClientDto clientdto){
        clientService.updateByDocument(document, clientdto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping(IClientEndPoint.DELETE_CLIENT)
    public ResponseEntity deleteClient(@PathVariable String document){
        clientService.deleteByDocument(document);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping(IClientEndPoint.FIND_CUSTOM_FIELD_ORDER)
    public ResponseEntity <List<ClientDto>> findByCustomFieldAndOrder2(@RequestParam("orderBy") String fieldCriteria,
                                                                       @RequestParam("direction") String orderCriteria){
        return ResponseEntity.status(HttpStatus.OK)
                .body(clientService.findAllByCustomFieldAndOrder(fieldCriteria, orderCriteria));
    }
}