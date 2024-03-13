package org.globant.restaurant.service.Order;

import org.globant.restaurant.commons.constans.response.client.IClientResponse;
import org.globant.restaurant.commons.constans.response.product.IProductResponse;
import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.entity.OrderEntity;
import org.globant.restaurant.entity.ProductEntity;
import org.globant.restaurant.exceptions.EntityNotFoundException;
import org.globant.restaurant.mapper.OrderConverter;
import org.globant.restaurant.model.request.OrderSaveRequest;
import org.globant.restaurant.model.OrderViewDTO;
import org.globant.restaurant.repository.Client.IClientRepository;
import org.globant.restaurant.repository.Order.IOrderRepository;
import org.globant.restaurant.repository.Product.IProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class OrderServiceImpl implements IOrderService{

    private final IOrderRepository orderRepository;

    private final IProductRepository productRepository;

    private final IClientRepository clientRepository;

    private final OrderConverter orderConverter;



    public OrderServiceImpl(IOrderRepository orderRepository, IProductRepository productRepository, IClientRepository clientRepository, OrderConverter orderConverter) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        this.orderConverter = orderConverter;
    }

    private final double TAX = 0.19D;

    @Override
    public OrderViewDTO updateByUUID(String orderUuid, LocalDateTime deliveredTimestamp){
        Optional<OrderEntity> optionalOrder = orderRepository.findByUuid(orderUuid);
        //TODO: Add validations
        if (optionalOrder.isPresent()) {
            optionalOrder.get().setDeliveryDate(deliveredTimestamp);
            optionalOrder.get().setDelivered(Boolean.TRUE);
            orderRepository.save(optionalOrder.get());
            return orderConverter.entityToDto(optionalOrder.get());
        } throw new EntityNotFoundException("Order not found");
    }

    @Override
    public OrderViewDTO save(OrderSaveRequest orderSaveRequestDTO){
        //TODO: Validate incoming data
        // Check existence of the entities.
        Optional<ProductEntity> productEntity = productRepository.findByUuidAndAvailableIsTrue(orderSaveRequestDTO.getProductUuid());
        Optional<ClientEntity> clientEntity = clientRepository.findByDocument(orderSaveRequestDTO.getClientDocument());

        if (productEntity.isEmpty()) {
            throw new EntityNotFoundException(IProductResponse.PRODUCT_NOT_FOUND);
        }if(clientEntity.isEmpty()){
            throw new EntityNotFoundException(IClientResponse.CLIENT_NOT_EXIST);
        }

        OrderViewDTO orderViewDTO = OrderViewDTO.builder()
            .creationDateTime(LocalDateTime.now())
            .clientDocument(clientEntity.get().getDocument())
            .productUUID(UUID.randomUUID().toString())
            .quantity(orderSaveRequestDTO.getQuantity())
            .extraInformation(orderSaveRequestDTO.getExtraInformation())
            .subTotal(orderSaveRequestDTO.getQuantity() * productEntity.get().getPrice())
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

    //@Override
    //public String deliverOrder() {
       // return null;
    //}

}
