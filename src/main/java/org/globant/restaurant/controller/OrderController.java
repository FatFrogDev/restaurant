package org.globant.restaurant.controller;

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
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("")
    public ResponseEntity<OrderViewDTO> createOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(request));
    }

    @PatchMapping("/{uuid}/delivered/{timestamp}")
    public ResponseEntity<OrderViewDTO> updateOrder(@PathVariable UUID uuid, @PathVariable LocalDateTime timestamp) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateByUUID(uuid, timestamp));
    }
}
