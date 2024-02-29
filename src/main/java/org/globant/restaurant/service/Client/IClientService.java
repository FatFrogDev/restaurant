package org.globant.restaurant.service.Client;

public interface IClientService {
    String createClient(String UUID);
    String findClientByDocument(String document);
    String updateClientByDocument(String document);
    String deleteClientByDocument(String document);
}
