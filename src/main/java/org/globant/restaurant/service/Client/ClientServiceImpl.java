package org.globant.restaurant.service.Client;

import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.mapper.ClientConverter;
import org.globant.restaurant.model.ClientDto;
import org.globant.restaurant.repository.Client.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    IClientRepository clientRepository;
    @Autowired
    ClientConverter clientConverter;

    @Override
    public String createClient(ClientDto clientDto){
        try{
            Optional<ClientEntity> find = this.clientRepository.findById(clientDto.getUuid());
            if(find.isEmpty()) {
                ClientEntity clientEntity = this.clientConverter.convertClientDtoToClientEntity(clientDto);
                this.clientRepository.save(clientEntity);
                return "ok";
            }
            else{
                return "Bad";
            }
        }
        catch (Exception e)
        {
            return HttpStatus.INTERNAL_SERVER_ERROR + String.format("Exception", e);
        }
    }

    @Override
    public String updateClientByDocument(String document) {
        return "In process";
    }

    @Override
    public String deleteClientByDocument(String document) {
        return "In process";
    }

    @Override
    public ClientEntity findClientByDocument(String document) {
        try{
            Optional<ClientEntity> clientEntity = this.clientRepository.findClientByDocument(document);
            return clientEntity.orElse(null);
        }
        catch (Exception e){
            return null;
        }
    }
}
