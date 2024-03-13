package org.globant.restaurant.service.Order;

import org.globant.restaurant.model.request.OrderSaveRequest;
import org.globant.restaurant.model.OrderViewDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IOrderService {
    String updateOrderByUUID(UUID id, OrderViewDTO orderViewDTO);

      OrderViewDTO createOrder(OrderSaveRequest request);

    String deliverOrder();

    ResponseEntity<?> save(OrderSaveRequest orderSaveRequest);
}
