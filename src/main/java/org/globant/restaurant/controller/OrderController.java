package org.globant.restaurant.controller;

import org.globant.restaurant.model.OrderSaveDTO;
import org.globant.restaurant.service.Client.ClientServiceImpl;
import org.globant.restaurant.service.Order.IOrderService;
import org.globant.restaurant.service.Order.OrderServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oder")
public class OrderController {

    IOrderService orderService
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }
    @PostMapping("")
    public ResponseEntity<OrderSaveDTO> saveOrder(@RequestBody OrderSaveDTO orderDaveDTO){
        return orderDaveDTO.save(OrderSaveDTO);
    }
}
