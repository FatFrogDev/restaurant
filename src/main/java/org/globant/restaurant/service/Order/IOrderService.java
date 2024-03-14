package org.globant.restaurant.service.Order;

import org.globant.restaurant.model.request.OrderRequest;
import org.globant.restaurant.model.OrderViewDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IOrderService {
    OrderViewDTO updateByUUID(String orderUuid, LocalDateTime deliveredTimestamp);

    OrderViewDTO save(OrderRequest request);

    boolean orderIsAlreadyDelivered(String orderUuid);

//     String deliverOrder();
}
