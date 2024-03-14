package org.globant.restaurant.service.Order;

import lombok.AllArgsConstructor;
import org.globant.restaurant.commons.constans.response.client.IClientResponse;
import org.globant.restaurant.commons.constans.response.order.IOrderResponse;
import org.globant.restaurant.commons.constans.response.product.IProductResponse;
import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.entity.OrderEntity;
import org.globant.restaurant.entity.ProductEntity;
import org.globant.restaurant.exceptions.EntityNotFoundException;
import org.globant.restaurant.exceptions.EntityValidationException;
import org.globant.restaurant.exceptions.OrderIsAlreadyDelivered;
import org.globant.restaurant.mapper.OrderConverter;
import org.globant.restaurant.model.request.OrderRequest;
import org.globant.restaurant.model.OrderViewDTO;
import org.globant.restaurant.repository.Client.IClientRepository;
import org.globant.restaurant.repository.Order.IOrderRepository;
import org.globant.restaurant.repository.Product.IProductRepository;
import org.globant.restaurant.validators.OrderValidators;
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
    public OrderViewDTO updateByUUID(String orderUuid, LocalDateTime deliveredTimestamp) { // TODO: validate timestamp format

        Optional<OrderEntity> optionalOrder = orderRepository.findByUuid(orderUuid);

        if (optionalOrder.isPresent()) {
            if (!orderIsAlreadyDelivered(orderUuid)) {
                optionalOrder.get().setDeliveryDate(deliveredTimestamp);
                optionalOrder.get().setDelivered(Boolean.TRUE);
                orderRepository.save(optionalOrder.get());
                return orderConverter.entityToDto(optionalOrder.get());
            }
            throw new OrderIsAlreadyDelivered(IOrderResponse.ORDER_ALREADY_DELIVERED);
        }
        throw new EntityNotFoundException(IOrderResponse.ORDER_NOT_FOUND);
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
                .grandTotal
                        (orderRequest.getQuantity() * productEntity.get().getPrice()
                                + (productEntity.get().getPrice() * TAX))
                .delivered(Boolean.FALSE)
                .deliveredDate(null)
                .build();

        OrderEntity savingOrder = orderConverter.dtoToEntity(orderViewDTO);
        savingOrder.setUuid(UUID.randomUUID().toString());
        savingOrder.setClient(clientEntity.get());
        savingOrder.setProduct(productEntity.get());

        return orderConverter.entityToDto(orderRepository.save(savingOrder));
        }

        @Override
        public boolean orderIsAlreadyDelivered(String orderUuid){
            Optional<OrderEntity> optionalOrder = orderRepository.findByUuidAndDeliveredIsTrue(orderUuid);
            return optionalOrder.isPresent();
        }
}
