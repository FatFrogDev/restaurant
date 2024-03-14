package org.globant.restaurant.repository.Order;

import org.globant.restaurant.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public interface IOrderRepository extends JpaRepository <OrderEntity, Long>{

    Optional<OrderEntity> findByUuid(String uuid);

    Optional<OrderEntity> findByUuidAndDeliveredIsTrue(String uuid);
}
