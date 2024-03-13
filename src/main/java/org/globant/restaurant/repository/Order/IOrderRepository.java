package org.globant.restaurant.repository.Order;

import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface IOrderRepository extends JpaRepository <OrderEntity, Long>{

    Optional<OrderEntity> findByUuid(UUID uuid);



}
