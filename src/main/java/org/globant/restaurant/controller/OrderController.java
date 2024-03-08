package org.globant.restaurant.controller;

import org.globant.restaurant.entity.OrderEntity;
import org.globant.restaurant.model.OrderSaveDTO;
import org.globant.restaurant.model.OrderViewDTO;
import org.globant.restaurant.service.Client.ClientServiceImpl;
import org.globant.restaurant.service.Order.IOrderService;
import org.globant.restaurant.service.Order.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderViewDTO> createOrder(@RequestBody OrderViewDTO orderViewDTO) {
        OrderViewDTO orderViewDTO1 = orderService.createOrder(orderViewDTO);
        return new ResponseEntity<>(orderViewDTO1, HttpStatus.CREATED);
    }

    private OrderViewDTO mapToOrderViewDTO(OrderSaveDTO createdOrder) {
        return null;
    }
}
