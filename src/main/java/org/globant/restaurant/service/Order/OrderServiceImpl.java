package org.globant.restaurant.service.Order;

import lombok.AllArgsConstructor;
import org.globant.restaurant.commons.constans.response.client.IClientResponse;
import org.globant.restaurant.commons.constans.response.order.IOrderResponse;
import org.globant.restaurant.commons.constans.response.product.IProductResponse;
import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.entity.OrderEntity;
import org.globant.restaurant.entity.ProductEntity;
import org.globant.restaurant.exceptions.EntityNotFoundException;
import org.globant.restaurant.mapper.OrderConverter;
import org.globant.restaurant.model.request.OrderRequest;
import org.globant.restaurant.model.OrderViewDTO;
import org.globant.restaurant.repository.Client.IClientRepository;
import org.globant.restaurant.repository.Order.IOrderRepository;
import org.globant.restaurant.repository.Product.IProductRepository;
import org.globant.restaurant.validators.OrderValidators;
import org.modelmapper.ValidationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.UUID;



@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private IOrderRepository orderRepository;

    private IProductRepository productRepository;

    private IClientRepository clientRepository;

    private OrderConverter orderConverter;

    private OrderValidators validators;

    private final double TAX = 0.19D;

    @Override
    public OrderViewDTO updateByUUID(String orderUuid, LocalDateTime deliveredTimestamp) {


        Optional<OrderEntity> optionalOrder = orderRepository.findByUuid(orderUuid);

        OrderEntity order = optionalOrder.orElseThrow(() -> new EntityNotFoundException(IOrderResponse.ORDER_NOT_FOUND));

        order.setDeliveryDate(deliveredTimestamp);
        order.setDelivered(Boolean.TRUE);
        orderRepository.save(order);
        return orderConverter.entityToDto(order);
    }

    @Override
    public OrderViewDTO save(OrderRequest orderRequest){
        validators.isSavableOrder(orderRequest);

        Optional<ProductEntity> productEntity = productRepository.findByUuidAndAvailableIsTrue(orderRequest.getProductUuid());
        Optional<ClientEntity> clientEntity = clientRepository.findByDocument(orderRequest.getClientDocument());

        if (productEntity.isEmpty()) {
            throw new EntityNotFoundException(IProductResponse.PRODUCT_NOT_FOUND);
        }if(clientEntity.isEmpty()){
            throw new EntityNotFoundException(IClientResponse.CLIENT_NOT_EXIST);
        }

        OrderViewDTO orderViewDTO = OrderViewDTO.builder()
                .creationDateTime(LocalDateTime.now())
                .clientDocument(clientEntity.get().getDocument())
                .productUUID(UUID.randomUUID().toString())
                .quantity(orderRequest.getQuantity())
                .extraInformation(orderRequest.getExtraInformation())
                .subTotal(orderRequest.getQuantity() * productEntity.get().getPrice())
                .tax(TAX)
                .grandTotal(productEntity.get().getPrice() + (productEntity.get().getPrice() * TAX))
                .delivered(Boolean.FALSE)
                .deliveredDate(null)
                .build();

        OrderEntity savingOrder = orderConverter.dtoToEntity(orderViewDTO);
        savingOrder.setUuid(UUID.randomUUID().toString());
        savingOrder.setClient(clientEntity.get());
        savingOrder.setProduct(productEntity.get());

        return orderConverter.entityToDto(orderRepository.save(savingOrder));
        }
}
