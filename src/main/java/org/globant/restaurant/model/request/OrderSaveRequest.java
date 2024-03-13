package org.globant.restaurant.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderSaveRequest {

    private String clientDocument;

    private UUID productUUID;

    private  int quantity;

    private String extraInformation;

}
