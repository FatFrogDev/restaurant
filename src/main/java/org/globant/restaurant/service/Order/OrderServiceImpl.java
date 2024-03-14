package org.globant.restaurant.service.Order;

import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.entity.OrderEntity;
import org.globant.restaurant.entity.ProductEntity;
import org.globant.restaurant.exceptions.EntityHasNoDifferentDataException;
import org.globant.restaurant.exceptions.EntityNotFoundException;
import org.globant.restaurant.mapper.OrderConverter;
import org.globant.restaurant.model.request.OrderRequest;
import org.globant.restaurant.model.OrderViewDTO;
import org.globant.restaurant.repository.Client.IClientRepository;
import org.globant.restaurant.repository.Order.IOrderRepository;
import org.globant.restaurant.repository.Product.IProductRepository;
import org.globant.restaurant.validators.OrderValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.UUID;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private OrderValidators validators;

    private final double TAX = 0.19D;

    @Override
    public OrderViewDTO updateByUUID(UUID orderUuid, LocalDateTime deliveredTimestamp) throws Exception {
        try {
            Optional<OrderEntity> optionalOrder = orderRepository.findByUuid(orderUuid);

            OrderEntity order = optionalOrder.orElseThrow(() -> new EntityNotFoundException("Order is empty"));

            order.setDeliveryDate(deliveredTimestamp);
            order.setDelivered(Boolean.TRUE);
            orderRepository.save(order);
            return orderConverter.entityToDto(order);

        } catch (EntityNotFoundException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public OrderViewDTO save(OrderRequest request){
        try {
            OrderViewDTO orderViewDTO = new OrderViewDTO();

            if (!validators.isSavableOrder(request))  {
                throw new EntityNotFoundException("Validate data");
            }

            Optional<ProductEntity> productResult = productRepository.findByUuidAndAvailableIsTrue(
                    request.getProductUuid().toString());
            Optional<ClientEntity> clientResult = clientRepository.findByDocument(request.getClientDocument());

            ClientEntity client = clientResult.orElseThrow(() -> new EntityNotFoundException("Client is empty"));
            ProductEntity product = productResult.orElseThrow(() -> new EntityNotFoundException("Product is empty"));

            double price = product.getPrice();
            int quantity = request.getQuantity();
            orderViewDTO.setClientDocument(client.getDocument());
            orderViewDTO.setProductUUID(product.getUuid());
            orderViewDTO.setQuantity(request.getQuantity());
            orderViewDTO.setExtraInformation(request.getExtraInformation());
            orderViewDTO.setCreationDateTime(LocalDateTime.now());
            orderViewDTO.setUuid(UUID.randomUUID());

            double subtotal = quantity * price;
            orderViewDTO.setSubTotal(subtotal);

            double tax = subtotal * 0.19;
            orderViewDTO.setTax(tax);

            double granTotal = subtotal + tax;
            orderViewDTO.setGrandTotal(granTotal);

            OrderEntity order = orderConverter.dtoToEntity(orderViewDTO);
            order.setClient(client);
            order.setProduct(product);
            return orderConverter.entityToDto(orderRepository.save(order));

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
