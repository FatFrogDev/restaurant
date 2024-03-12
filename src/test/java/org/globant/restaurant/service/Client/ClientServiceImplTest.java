package org.globant.restaurant.service.Client;

import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.exceptions.EntityHasNoDifferentDataException;
import org.globant.restaurant.mapper.ClientConverter;
import org.globant.restaurant.model.ClientDto;
import org.globant.restaurant.repository.Client.IClientRepository;
import org.globant.restaurant.validators.ClientValidators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.globant.restaurant.exceptions.EntityValidationException;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    private ClientDto clientDto;

    private ClientEntity clientEntity;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private  IClientRepository clientRepository;

    @Mock
    private  ClientConverter converter;

    @Mock
    private  ClientValidators validator;

    @BeforeEach
    public void setUp(){ //TODO: complete the builder.
        clientEntity = clientEntity.builder()
                .uuid(UUID.randomUUID())
                .document("123456789")
                .fullName("Juan Perez")
                .email("emai@gmail.com")
                .phone("123456789")
                .deliveryAddress("Calle 123")
                .build();

        clientDto = clientDto.builder()
                .document("123456789")
                .fullName("Juan Perez")
                .email("emai@gmail.com")
                .phone("123456789")
                .deliveryAddress("Calle 123")
                .build();
    }

    @Test
    void shouldSaveAClientSuccesfuly(){
        Mockito.when(clientRepository.findByDocument(clientDto.getDocument()))
                .thenReturn(Optional.empty());
        Mockito.when(converter.convertClientDtoToClientEntity(clientDto))
                .thenReturn(clientEntity);
        Mockito.when(validator.isSavableClient(clientDto))
                .thenReturn(Boolean.TRUE);
        Mockito.when(clientRepository.save(clientEntity))
                .thenReturn(clientEntity);
        var response = clientService.save(clientDto);

        Assertions.assertEquals(clientDto.getDocument(), response.getDocument());
    }

    @Test
    void shouldReturnErrorOrSameDocumentNumber() {
        Mockito.when(clientRepository.findByDocument(clientDto.getDocument()))
                .thenReturn(Optional.of(clientEntity));
        /*
        Mockito.when(converter.convertClientDtoToClientEntity(clientDto))
                .thenReturn(clientEntity);
        Mockito.when(validator.isSavableClient(clientDto))
                .thenReturn(Boolean.FALSE);*/

        Assertions.assertThrows(EntityHasNoDifferentDataException.class, () -> {
            clientService.updateByDocument(clientDto.getDocument(), clientDto);
        });
    }

    @Test
    void deleteByDocument() {
    }

    @Test
    void findClientByDocument() {
        String document = "123456789";
        ClientEntity clientEntity = new ClientEntity();
        Mockito.when(clientRepository.findByDocument(document))
                .thenReturn(Optional.of(clientEntity));

        ClientDto expectedClientDto = new ClientDto();
        Mockito.when(converter.convertClientEntityToClientDTO(clientEntity))
                .thenReturn(expectedClientDto);

        ClientDto result = clientService.findClientByDocument(document);

        Assertions.assertEquals(expectedClientDto, result);
    }

    @Test
    void findAllByCustomFieldAndOrder() {
    }
}