package org.globant.restaurant.service.Order;

import org.globant.restaurant.model.request.OrderRequest;
import org.globant.restaurant.model.OrderViewDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IOrderService {
    OrderViewDTO updateByUUID(UUID orderUuid, LocalDateTime deliveredTimestamp) throws Exception;

    OrderViewDTO save(OrderRequest request);

//     String deliverOrder();
}
