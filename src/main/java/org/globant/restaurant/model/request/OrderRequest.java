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
public class OrderRequest {

    private String clientDocument;

    private String  productUuid;

    private  int quantity;

    private String extraInformation;

}
