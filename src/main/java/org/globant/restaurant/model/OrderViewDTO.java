package org.globant.restaurant.model;

// import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderViewDTO {
    // private UUID uuid;
    private LocalDate creationDateTime;
    private String clientDocument;
    private UUID productUUID;
    private int quantity;
    private String extraInformation;
    private String subTotal;
    private Double tax;
    private Double grandTotal;
    private boolean delivered;
    private LocalDate deliveryDate;
}
