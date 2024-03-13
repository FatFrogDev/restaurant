package org.globant.restaurant.controller;

import org.globant.restaurant.commons.constans.endPoints.order.IOrderEndPoint;
import org.globant.restaurant.model.OrderViewDTO;
import org.globant.restaurant.model.request.OrderSaveRequest;
import org.globant.restaurant.service.Order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping(IOrderEndPoint.BASE_URL)
public class OrderController {

    @Autowired
    private IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(IOrderEndPoint.CREATE_ORDER)
    public ResponseEntity<OrderViewDTO> createOrder(@RequestBody OrderSaveRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(request));
    }

    @PatchMapping(IOrderEndPoint.UPDATE_BY_UUID)
    public ResponseEntity<OrderViewDTO> updateByUUID(@PathVariable String uuid, @PathVariable LocalDateTime timestamp){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateByUUID(uuid, timestamp));
    }
}
