package org.globant.restaurant.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    protected UUID uuid;
    private String document;
    private String name;
    private String email;
    private String phone;
    private String deliveryAddress;
}
