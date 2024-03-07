package org.globant.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderSaveDTO {
    private String clientDocument;
    private String productUUID;
    private  int quantity;
    private String extraInformation;
}
