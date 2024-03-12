package org.globant.restaurant.mapper;

import lombok.extern.log4j.Log4j2;
import org.globant.restaurant.entity.OrderEntity;
import org.globant.restaurant.commons.helpers.HelperMapper;
import org.globant.restaurant.model.OrderViewDTO;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class OrderConverter {

    public OrderViewDTO convertOrderEntityToOrderViewDTO(OrderEntity orderEntity) {
        OrderViewDTO orderViewDTO = new OrderViewDTO();
        try {
            orderViewDTO = HelperMapper.modelMapper().map(orderEntity, OrderViewDTO.class);
        } catch (Exception e) {
            log.error("Error");
        }
        return orderViewDTO;
    }
}
