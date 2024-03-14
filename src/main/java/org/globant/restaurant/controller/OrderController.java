package org.globant.restaurant.controller;

import lombok.AllArgsConstructor;
import org.globant.restaurant.commons.constans.endPoints.order.IOrderEndPoint;
import org.globant.restaurant.model.OrderViewDTO;
import org.globant.restaurant.model.request.OrderRequest;
import org.globant.restaurant.service.Order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(IOrderEndPoint.BASE_URL)
public class OrderController {

    private IOrderService orderService;

    @PostMapping(IOrderEndPoint.CREATE_ORDER)
    public ResponseEntity<OrderViewDTO> createOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(request));
    }

    @PatchMapping(IOrderEndPoint.UPDATE_BY_UUID)
    public ResponseEntity<OrderViewDTO> updateOrder(@PathVariable String uuid, @PathVariable LocalDateTime timestamp){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateByUUID(uuid, timestamp));
    }
}
