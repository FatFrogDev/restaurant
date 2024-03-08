package org.globant.restaurant.mapper;

import lombok.extern.log4j.Log4j2;
import org.globant.restaurant.entity.OrderEntity;
import org.globant.restaurant.helpers.HelperMapper;
import org.globant.restaurant.model.OrderSaveDTO;
import org.globant.restaurant.model.OrderViewDTO;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class OrderConverter {

    public OrderViewDTO EntityToDto(OrderEntity orderEntity) {
      OrderViewDTO orderDto = new OrderViewDTO();
      orderDto.setClientDocument(orderEntity.getClientDocument());
      orderDto.setProductUUID(orderEntity.getProductUuid());
      orderDto.setQuantity(orderEntity.getQuantity());
      orderDto.setExtraInformation(orderEntity.getExtraInformation());
      orderDto.setDelivered(orderEntity.isDelivered());
      orderDto.setSubTotal(orderEntity.getSubTotal());
      orderDto.setTax(orderEntity.getTax());
      orderDto.setGrandTotal(orderEntity.getGranTotal());
      orderDto.setCreationDateTime(orderEntity.getCreationDateTime());
      orderDto.setDeliveryDate(orderEntity.getDeliveryDate());

        return orderDto;
    }

    public OrderEntity DtoToEntity(OrderViewDTO orderViewDTO) {
        return OrderEntity.builder()
                .clientDocument(orderViewDTO.getClientDocument())
                .productUuid(orderViewDTO.getProductUUID())
                .quantity(orderViewDTO.getQuantity())
                .extraInformation(orderViewDTO.getExtraInformation())
                .subTotal(orderViewDTO.getSubTotal())
                .tax(orderViewDTO.getTax())
                .granTotal(orderViewDTO.getGrandTotal())
                .creationDateTime(orderViewDTO.getCreationDateTime())
                .deliveryDate(orderViewDTO.getDeliveryDate())
                .delivered(orderViewDTO.isDelivered())
                .build();

    }


}
