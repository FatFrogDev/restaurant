package org.globant.restaurant.repository.Client;

import org.globant.restaurant.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, UUID> {
  @Query(value = "SELECT * FROM client WHERE document = :document", nativeQuery = true)
    Optional<ClientEntity>findClientByDocument(@Param("document") String document);
}