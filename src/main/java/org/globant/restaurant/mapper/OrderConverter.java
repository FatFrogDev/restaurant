package org.globant.restaurant.mapper;

import lombok.extern.log4j.Log4j2;
import org.globant.restaurant.entity.OrderEntity;
import org.globant.restaurant.model.OrderViewDTO;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class OrderConverter {

    public OrderViewDTO entityToDto(OrderEntity orderEntity) {

        // Uses "findFirst" to get the product id due to is assumed that the order has a collection of the same product
            String productUUID =
                    orderEntity.getProduct().getUuid();

            return OrderViewDTO.builder()
                .uuid(orderEntity.getUuid())
                .creationDateTime(orderEntity.getCreationDateTime())
                .clientDocument(orderEntity.getClient().getDocument())
                .productUUID(productUUID)
                .quantity(orderEntity.getQuantity())
                .extraInformation(orderEntity.getExtraInformation())
                .subTotal(orderEntity.getSubTotal())
                .tax(orderEntity.getTax())
                .grandTotal(orderEntity.getGrandTotal())
                .delivered(orderEntity.isDelivered())
                .deliveredDate(orderEntity.getDeliveryDate())
                .build();
    }

    public OrderEntity dtoToEntity(OrderViewDTO orderViewDTO) {
        return OrderEntity.builder()
                .creationDateTime(orderViewDTO.getCreationDateTime())
                // TODO: verify!
                .quantity(orderViewDTO.getQuantity())
                // TODO: verify!
                .extraInformation(orderViewDTO.getExtraInformation())
                .subTotal(orderViewDTO.getSubTotal())
                .tax(orderViewDTO.getTax())
                .grandTotal(orderViewDTO.getGrandTotal())
                .delivered(orderViewDTO.isDelivered())
                .deliveryDate(orderViewDTO.getDeliveredDate())
                .build();
    }
}