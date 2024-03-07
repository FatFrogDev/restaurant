package org.globant.restaurant.service.Order;

import org.globant.restaurant.entity.OrderEntity;
import org.globant.restaurant.model.OrderSaveDTO;
import org.springframework.http.ResponseEntity;

public interface IOrderService {
    String updateOrderByUUID(String id);

    String createOrder(String UUID);

    OrderSaveDTO createOrder(OrderSaveDTO orderSaveDTO);

    String deliverOrder();

    ResponseEntity<?> save(OrderSaveDTO orderSaveDTO);
}
