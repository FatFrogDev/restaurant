package org.globant.restaurant.service.Client;

import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.exceptions.EntityAlreadyExistsException;
import org.globant.restaurant.exceptions.EntityHasNoDifferentDataException;
import org.globant.restaurant.exceptions.EntityNotFoundException;
import org.globant.restaurant.exceptions.InvalidQueryArgsException;
import org.globant.restaurant.mapper.ClientConverter;
import org.globant.restaurant.model.ClientDto;
import org.globant.restaurant.repository.Client.IClientRepository;
import org.globant.restaurant.validators.ClientValidators;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    private final ClientConverter converter;

    private final ClientValidators validator;

    public ClientServiceImpl(IClientRepository clientRepository, ClientConverter converter, ClientValidators validator) {
        this.clientRepository = clientRepository;
        this.converter = converter;
        this.validator = validator;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        Optional<ClientEntity> client = clientRepository.findByDocument(clientDto.getDocument());
        ClientEntity clientEntity = converter.convertClientDtoToClientEntity(clientDto);
        if (!client.isPresent()){
            if(validator.isSavableClient(clientDto)){
                clientRepository.save(clientEntity); // TODO: refactor this to return the saved entity.
                return clientDto;
            }; throw new EntityHasNoDifferentDataException("Client has no different data");
        }
        throw new EntityAlreadyExistsException("Client already exists");
    }

    @Override
    public void updateByDocument(String document, ClientDto clientDto) {
        clientDto.setDocument(document);
        Optional<ClientEntity> optionalClient = clientRepository.findByDocument(clientDto.getDocument());

        if (optionalClient.isPresent()) {
            if (validator.clientIsUpdatable(clientDto, optionalClient.get())){
                ClientEntity clientEntity = converter.convertClientDtoToClientEntity(clientDto);
                clientEntity.setUuid(optionalClient.get().getUuid());
                clientRepository.save(clientEntity);
            }
        } throw new EntityNotFoundException("Client does not exists");
    }

    @Override
    public void deleteByDocument(String document) {
        Optional<ClientEntity> optionalClient = clientRepository.findByDocument(document);
        if (optionalClient.isPresent()) {
            clientRepository.deleteByDocument(document);
        } throw new EntityNotFoundException("Client to delete exists");
    }

    @Override
    public ClientDto findClientByDocument(String document) {
        Optional<ClientEntity> optionalClient = clientRepository.findByDocument(document);
        if (optionalClient.isPresent()) {
            return converter.convertClientEntityToClientDTO(optionalClient.get());
        } throw new EntityNotFoundException("Client does not exists");
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
                    .map(clientEntity -> converter.convertClientEntityToClientDTO(clientEntity))
                    .toList();
        }
        throw new InvalidQueryArgsException("Invalid arguments provided order must be 'asc' or 'desc' & field must be 'name', 'document' or 'address'");
    }
}