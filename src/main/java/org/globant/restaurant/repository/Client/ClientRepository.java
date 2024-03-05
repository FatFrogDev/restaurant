package org.globant.restaurant.repository.Client;

import jakarta.annotation.PostConstruct;
import org.globant.restaurant.entity.ClientEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ClientRepository implements IClientRepository {

    private List<ClientEntity> clients;

    @PostConstruct
    public void init() {
        clients = List.of(
                ClientEntity.builder()
                        .uuid(UUID.randomUUID())
                        .document("CC-101287166")
                        .name("Andrea Fernandez")
                        .email("andresf2@gmail.com")
                        .phone("3112034591")
                        .deliveryAddress("Calle 123 # 45-67")
                        .build(),
                ClientEntity.builder()
                        .uuid(UUID.randomUUID())
                        .document("CC-9678172")
                        .name("MariaTeresa")
                        .email("mteresa12@gmail.com")
                        .phone("3126511311")
                        .deliveryAddress("Calle 45 # 67-89")
                        .build()
        );
    }

    @Override
    public String createClient(String uuid){return "Client" + uuid + "created successfully!";}


    public String updateClientByDocument(String document){
        return "Client " + document + " updated successfully!";
    }

    public String deleteClientByDocument(String document) {
        return  "Client " + document + " deleted successfully!";
    }

    @Override
    public ClientEntity findClientByDocument(String document) {
        return clients.stream()
                .filter(client -> client.getDocument().equals(document))
                .findFirst()
                .orElse(null);
    }
}
