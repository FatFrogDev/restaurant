package org.globant.restaurant.controller;

import org.globant.restaurant.model.OrderViewDTO;
import org.globant.restaurant.model.request.OrderSaveRequest;
import org.globant.restaurant.service.Order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping
    public ResponseEntity<OrderViewDTO> createOrder(@RequestBody OrderSaveRequest request) {
        return new ResponseEntity<>(orderService.createOrder(request), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity updateByUUID(@PathVariable UUID uuid, @RequestBody OrderViewDTO orderViewDTO){
        orderService.updateOrderByUUID(uuid, orderViewDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
