package org.globant.restaurant.service.Order;

import org.globant.restaurant.entity.OrderEntity;
import org.globant.restaurant.mapper.OrderConverter;
import org.globant.restaurant.model.OrderSaveDTO;
import org.globant.restaurant.repository.Order.IOrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class OrderServiceImpl implements IOrderService{
    IOrderRepository orderRepository;

    OrderConverter converter;

    @Override
    public String updateOrderByUUID(String idOrder) {
        return null;
    }

    @Override
    public ResponseEntity<?> save(OrderSaveDTO orderSaveDTO) {
        OrderEntity orderEntity = converter.convertOrderSaveDTOToOrderEntity(OrderSaveDTO);
        System.out.println(orderEntity.toString());
        try {
            orderRepository.save(orderEntity);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(orderEntity);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }
}
