package org.globant.restaurant.model;


import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderViewDTO {
    private UUID uuid;
    private LocalDateTime creationDateTime;
    private String clientDocument;
    private String productUUID;
    private int quantity;
    private String extraInformation;
    private Double subTotal;
    private Double tax;
    private Double grandTotal;
    private boolean delivered;
    private LocalDateTime deliveredDate;
}
