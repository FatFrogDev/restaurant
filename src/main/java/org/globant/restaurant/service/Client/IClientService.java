package org.globant.restaurant.service.Client;


import org.globant.restaurant.model.ClientDto;
import org.springdoc.core.converters.models.Sort;

import java.util.List;


public interface IClientService {

    ClientDto save(ClientDto client);

    void updateByDocument(String document, ClientDto clientDto);

    void deleteByDocument(String document);

    ClientDto findClientByDocument(String document);

    List<ClientDto> findAllByCustomFieldAndOrder(String customField, String customOrder);
}
