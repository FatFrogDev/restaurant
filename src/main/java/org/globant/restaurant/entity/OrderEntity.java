package org.globant.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    private LocalDate requestDate;
    private ProductEntity product;
    private ClientEntity client;
    private int quantity; // TODO: add validation, number must be greater or equals to 1 and lower than 100
    private String additionalInformation;
    private boolean delivered;
    private LocalDate deliveryDate;
}
