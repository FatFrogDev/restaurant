package org.globant.restaurant.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private String document;
    private String fullName;
    private String email;
    private String phone;
    private String deliveryAddress;
}
