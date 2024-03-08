package org.globant.restaurant.service.Order;

import org.globant.restaurant.entity.OrderEntity;
import org.globant.restaurant.model.OrderSaveDTO;
import org.globant.restaurant.model.OrderViewDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

public interface IOrderService extends CrudRepository<OrderEntity, Long> {
    String updateOrderByUUID(String id);

    String createOrder(String UUID);

    OrderViewDTO createOrder(OrderViewDTO orderViewDTO);

    String deliverOrder();

    ResponseEntity<?> save(OrderSaveDTO orderSaveDTO);
}
