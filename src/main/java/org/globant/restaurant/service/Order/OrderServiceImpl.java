package org.globant.restaurant.service.Order;

import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.entity.OrderEntity;
import org.globant.restaurant.entity.ProductEntity;
import org.globant.restaurant.exceptions.EntityNotFoundException;
import org.globant.restaurant.mapper.OrderConverter;
import org.globant.restaurant.mapper.ProductConverter;
import org.globant.restaurant.model.request.OrderSaveRequest;
import org.globant.restaurant.model.OrderViewDTO;
import org.globant.restaurant.repository.Client.IClientRepository;
import org.globant.restaurant.repository.Order.IOrderRepository;
import org.globant.restaurant.repository.Product.IProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class OrderServiceImpl implements IOrderService{

    private final IOrderRepository orderRepository;

    private final IProductRepository productRepository;

    private final IClientRepository clientRepository;

    private final OrderConverter orderConverter;

    public OrderServiceImpl(
            IProductRepository productRepository, IClientRepository clientRepository,
            OrderConverter orderConverter, IOrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        this.orderConverter = orderConverter;
        this.orderRepository = orderRepository;
    }

    @Override
    public String updateOrderByUUID(UUID id, OrderViewDTO orderViewDTO) {
        Optional<OrderEntity> existingOrderOptional = orderRepository.findByUuid(id);

        OrderEntity existingOrder = existingOrderOptional.orElseThrow(() -> new EntityNotFoundException("Order not found"));

        existingOrder.setClientDocument(orderViewDTO.getClientDocument());
        existingOrder.setProductUuid((Set<ProductEntity>) orderViewDTO.getProductUUID());
        orderRepository.save(existingOrder);

        return "Update Successfully";

    }

    public OrderViewDTO createOrder(OrderSaveRequest request){
        try {
            OrderViewDTO orderViewDTO = new OrderViewDTO();

            Optional<ProductEntity> productResult = productRepository.findByUuid(request.getProductUUID());
            Optional<ClientEntity> clientResult = clientRepository.findByDocument(request.getClientDocument());

            ClientEntity client = clientResult.orElseThrow(() -> new EntityNotFoundException("Client is empty"));
            ProductEntity product = productResult.orElseThrow(() -> new EntityNotFoundException("Product is empty"));

            double price = product.getPrice();
            int quantity = request.getQuantity();
            orderViewDTO.setClientDocument(client);
            orderViewDTO.setProductUUID(orderViewDTO.getProductUUID());
            orderViewDTO.setQuantity(request.getQuantity());
            orderViewDTO.setExtraInformation(request.getExtraInformation());
            orderViewDTO.setCreationDateTime(LocalDateTime.now());
            orderViewDTO.setUuid(UUID.randomUUID());

            double subtotal = quantity * price;
            orderViewDTO.setSubTotal(subtotal);

            double tax = subtotal* 0.19;
            orderViewDTO.setTax(tax);

            double granTotal = subtotal + tax;
            orderViewDTO.setGrandTotal(granTotal);

            OrderEntity order = orderConverter.dtoToEntity(orderViewDTO);
            return orderConverter.entityToDto(orderRepository.save(order));

        } catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @Override
    public String deliverOrder() {
        return null;
    }

    @Override
    public ResponseEntity<?> save(OrderSaveRequest orderSaveRequest) {
        return null;
    }


}
