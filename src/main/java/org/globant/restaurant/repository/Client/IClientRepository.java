package org.globant.restaurant.repository.Client;

import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.entity.ProductEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.LinkedList;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, UUID> {
    Optional<ClientEntity> findByDocument(String document);

    @Transactional
    void deleteByDocument(String document);

    @Query(value = "SELECT uuid, delivery_address, document, email, name, phone FROM clients ORDER BY :custom_field :custom_order", nativeQuery = true)
    Optional<LinkedList<ClientEntity>> findByCustomFieldAndOrder
            (@Param("custom_field") String custom_field,
             @Param("custom_order") String custom_order);
}
