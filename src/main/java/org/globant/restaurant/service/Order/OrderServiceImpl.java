package org.globant.restaurant.service.Order;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.entity.OrderEntity;
import org.globant.restaurant.entity.ProductEntity;
import org.globant.restaurant.mapper.OrderConverter;
import org.globant.restaurant.model.OrderViewDTO;
import org.globant.restaurant.repository.Client.IClientRepository;
import org.globant.restaurant.repository.Order.IOrderRepository;
import org.globant.restaurant.repository.Product.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Service
public class OrderServiceImpl{

    private IOrderRepository orderRepository;

    private OrderConverter converter;

    private ProductRepository productRepository;

    private IClientRepository clientRepository;

    private OrderConverter orderConverter;


    public OrderViewDTO createOrder(OrderViewDTO orderViewDTO){
        try {
            Optional<ProductEntity> productResult = this.productRepository.findById(orderViewDTO.getProductUUID().getId());
            Optional<ClientEntity> clientResult = this.clientRepository.findById(UUID.fromString(orderViewDTO.getClientDocument().getDocument()));
            if(productResult.isPresent()&&clientResult.isPresent()){
                ProductEntity prodct = productResult.get();
                double price = prodct.getPrice();
                Integer quantity = orderViewDTO.getQuantity();
                orderViewDTO.setClientDocument(orderViewDTO.getClientDocument());
                orderViewDTO.setProductUUID(orderViewDTO.getProductUUID());
                orderViewDTO.setQuantity(orderViewDTO.getQuantity());
                orderViewDTO.setExtraInformation(orderViewDTO.getExtraInformation());
                orderViewDTO.setCreationDateTime(LocalDateTime.now());
                orderViewDTO.setUuid(UUID.randomUUID());

                double subtotal = quantity * price;
                orderViewDTO.setSubTotal(subtotal);

                double tax = subtotal* 0.19;
                orderViewDTO.setTax(tax);

                double granTotal = subtotal + tax;
                orderViewDTO.setGrandTotal(granTotal);

                OrderEntity order = orderConverter.DtoToEntity(orderViewDTO);
                return orderConverter.EntityToDto(orderRepository.save(order));

            }else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }





}
