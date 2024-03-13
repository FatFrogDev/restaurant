package org.globant.restaurant.controller;

import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.exceptions.ClientHasDifferentDocumentException;
import org.globant.restaurant.model.ClientDto;
import org.globant.restaurant.service.Client.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ClientControllerTest {


    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientServiceImpl clientService;

    private ClientDto clientDto = new ClientDto();


    @BeforeEach
    void setUp(){


    }

    @Test
    void testShouldThrowClientHasDifferentDocumentException(){
        //Given
    }
}