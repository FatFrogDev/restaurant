package org.globant.restaurant.service.Order;

import org.globant.restaurant.model.request.OrderSaveRequest;
import org.globant.restaurant.model.OrderViewDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IOrderService {
    String updateByUUID(UUID id, OrderViewDTO orderViewDTO);

    OrderViewDTO save(OrderSaveRequest request);

//     String deliverOrder();
}
