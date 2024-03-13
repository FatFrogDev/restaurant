package org.globant.restaurant.model;

// import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.entity.ProductEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderViewDTO {
    private UUID uuid;
    private LocalDateTime creationDateTime;
    private ClientEntity clientDocument;
    private ProductEntity productUUID;
    private int quantity;
    private String extraInformation;
    private Double subTotal;
    private Double tax;
    private Double grandTotal;
    private boolean delivered;
    private LocalDateTime deliveryDate;
}
