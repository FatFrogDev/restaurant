package org.globant.restaurant.entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientEntity {
    protected UUID uuid;
    private String document;
    private String name;
    private String email;
    private String phone;
    private String deliveryAddress;
}
