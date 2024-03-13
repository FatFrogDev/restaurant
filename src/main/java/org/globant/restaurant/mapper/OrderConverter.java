package org.globant.restaurant.mapper;

import lombok.extern.log4j.Log4j2;
import org.globant.restaurant.entity.OrderEntity;
import org.globant.restaurant.entity.ProductEntity;
import org.globant.restaurant.model.OrderViewDTO;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Log4j2
public class OrderConverter {

    public OrderViewDTO entityToDto(OrderEntity orderEntity) {

          OrderViewDTO orderDto = new OrderViewDTO();
          orderDto.setClientDocument(orderEntity.getClientDocument());
          orderDto.setProductUUID((ProductEntity) orderEntity.getProductUuid());
          orderDto.setQuantity(orderEntity.getQuantity());
          orderDto.setExtraInformation(orderEntity.getExtraInformation());
          orderDto.setDelivered(orderEntity.isDelivered());
          orderDto.setSubTotal(orderEntity.getSubTotal());
          orderDto.setTax(orderEntity.getTax());
          orderDto.setGrandTotal(orderEntity.getGranTotal());
          orderDto.setCreationDateTime(orderEntity.getCreationDateTime());
          orderDto.setDeliveryDate(orderEntity.getDeliveryDate());
          orderDto.setUuid(orderEntity.getUuid());
        return orderDto;
    }

    public OrderEntity dtoToEntity(OrderViewDTO orderViewDTO) {
        return OrderEntity.builder()
                .clientDocument(orderViewDTO.getClientDocument())
                .productUuid((Set<ProductEntity>) orderViewDTO.getProductUUID())
                .quantity(orderViewDTO.getQuantity())
                .extraInformation(orderViewDTO.getExtraInformation())
                .subTotal(orderViewDTO.getSubTotal())
                .tax(orderViewDTO.getTax())
                .granTotal(orderViewDTO.getGrandTotal())
                .creationDateTime(orderViewDTO.getCreationDateTime())
                .deliveryDate(orderViewDTO.getDeliveryDate())
                .delivered(orderViewDTO.isDelivered())
                .uuid(orderViewDTO.getUuid())
                .build();

    }
}