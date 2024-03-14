package org.globant.restaurant.service.Client;

import lombok.AllArgsConstructor;
import org.globant.restaurant.commons.constans.response.client.IClientResponse;
import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.exceptions.*;
import org.globant.restaurant.mapper.ClientConverter;
import org.globant.restaurant.model.ClientDto;
import org.globant.restaurant.repository.Client.IClientRepository;
import org.globant.restaurant.validators.ClientValidators;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    private final ClientConverter converter;

    private final ClientValidators validator;

    @Override
    public ClientDto save(ClientDto clientDto) {
        Optional<ClientEntity> optionalClient = clientRepository.findByDocument(clientDto.getDocument());
        ClientEntity clientEntity = converter.convertClientDtoToClientEntity(clientDto);
        if (!optionalClient.isPresent()){
            if(validator.isSavableClient(clientDto)){
                return converter.convertClientEntityToClientDTO
                                (clientRepository.save(clientEntity));
            } throw new EntityHasNoDifferentDataException(IClientResponse.CLIENT_DIFFERENT_DATA);
        }
        throw new EntityAlreadyExistsException(IClientResponse.CLIENT_EXIST);
    }

    @Override
    public void updateByDocument(String document, ClientDto clientDto) {
        clientDto.setDocument(document);
        Optional<ClientEntity> optionalClient = clientRepository.findByDocument(clientDto.getDocument());

        if (optionalClient.isPresent()) {
            if( validator.clientIsUpdatable(clientDto, optionalClient.get()) ){
                System.out.println("actualizando");
                ClientEntity clientEntity = converter.convertClientDtoToClientEntity(clientDto);
                clientEntity.setUuid(optionalClient.get().getUuid());
                clientRepository.save(clientEntity);
            } else throw new EntityHasNoDifferentDataException(IClientResponse.CLIENT_DIFFERENT_DATA);
        } else throw new EntityNotFoundException(IClientResponse.CLIENT_NOT_EXIST);
    }

    @Override
    public void deleteByDocument(String document) {
        Optional<ClientEntity> optionalClient = clientRepository.findByDocument(document);
        if (optionalClient.isPresent()) {
            clientRepository.deleteByDocument(document);
        } else throw new EntityNotFoundException(IClientResponse.CLIENT_NOT_EXIST);
    }

    @Override
    public ClientDto findClientByDocument(String document){
            Optional<ClientEntity> optionalClient = clientRepository.findByDocument(document);
            if (optionalClient.isPresent()) {
                return converter.convertClientEntityToClientDTO(optionalClient.get());
            } throw new EntityNotFoundException(IClientResponse.CLIENT_NOT_EXIST);
    }

    @Override
    public List<ClientDto> findAllByCustomFieldAndOrder(String customField, String customOrder) {
        boolean queryIsValid = validator.validateCustomFieldAndOrderQuery(customField, customOrder);

        if (queryIsValid){
            customField = validator.transformCustomField(customField);

            Sort sort = customOrder.equalsIgnoreCase("desc")
                    ? Sort.by(customField).descending()
                    : Sort.by(customField).ascending();

            List<ClientEntity> clientEntities = clientRepository.findAll(sort);

            return clientEntities.stream()
                    .map(converter::convertClientEntityToClientDTO)
                    .toList();
        }
        throw new InvalidQueryArgsException(IClientResponse.CLIENT_INVALID_ARGUMENTS);
    }

    @Override
    public boolean existsByDocument(String document) {
        validator.clientDocumentIsValid(document);
        return clientRepository.existsByDocument(document);
    }
}