package org.globant.restaurant.service.Order;

import org.globant.restaurant.commons.constans.response.client.IClientResponse;
import org.globant.restaurant.commons.constans.response.product.IProductResponse;
import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.entity.OrderEntity;
import org.globant.restaurant.entity.ProductEntity;
import org.globant.restaurant.exceptions.EntityNotFoundException;
import org.globant.restaurant.mapper.ClientConverter;
import org.globant.restaurant.mapper.OrderConverter;
import org.globant.restaurant.mapper.ProductConverter;
import org.globant.restaurant.model.request.OrderSaveRequest;
import org.globant.restaurant.model.OrderViewDTO;
import org.globant.restaurant.repository.Client.IClientRepository;
import org.globant.restaurant.repository.Order.IOrderRepository;
import org.globant.restaurant.repository.Product.IProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class OrderServiceImpl implements IOrderService{

    private final IOrderRepository orderRepository;

    private final IProductRepository productRepository;

    private final IClientRepository clientRepository;

    private final OrderConverter orderConverter;

    private  final ProductConverter productConverter;

    private final ClientConverter clientConverter;

    public OrderServiceImpl(IOrderRepository orderRepository, IProductRepository productRepository, IClientRepository clientRepository, OrderConverter orderConverter, ProductConverter productConverter, ClientConverter clientConverter) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        this.orderConverter = orderConverter;
        this.productConverter = productConverter;
        this.clientConverter = clientConverter;
    }

    private final double TAX = 0.19D;

    @Override
    public String updateByUUID(UUID id, OrderViewDTO orderViewDTO) {
        Optional<OrderEntity> existingOrderOptional = orderRepository.findByUuid(id);

        OrderEntity existingOrder = existingOrderOptional.orElseThrow(() -> new EntityNotFoundException("Order not found"));

        //existingOrder.setClientDocument(orderViewDTO.getClientDocument());
       // existingOrder.setProductUuid((Set<ProductEntity>) orderViewDTO.getProductUUID());
        //orderRepository.save(existingOrder);

        return "Update Successfully";

    }

    @Override
    public OrderViewDTO save(OrderSaveRequest orderSaveRequestDTO){
        //TODO: Validate incoming data
        Optional<ProductEntity> productEntity = productRepository.findByUuid(orderSaveRequestDTO.getProductUuid());
        Optional<ClientEntity> clientEntity = clientRepository.findByDocument(orderSaveRequestDTO.getClientDocument());

        // Check existence of the entities.
        if (productEntity.isEmpty()) {
            throw new EntityNotFoundException(IProductResponse.PRODUCT_NOT_FOUND);
        }if(clientEntity.isEmpty()){
            throw new EntityNotFoundException(IClientResponse.CLIENT_NOT_EXIST);
        }
        // creates an OrderViewDto with the given data

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
            .deliveryDate(null)
            .build();

        // Creates an order entity and saves it into the database.
            OrderEntity savingOrder = orderConverter.dtoToEntity(orderViewDTO);
            savingOrder.setClient(clientEntity.get());
            savingOrder.setProduct(List.of(productEntity.get()));

            return orderConverter.entityToDto(orderRepository.save(savingOrder));
        }

    //@Override
    //public String deliverOrder() {
       // return null;
    //}

}
