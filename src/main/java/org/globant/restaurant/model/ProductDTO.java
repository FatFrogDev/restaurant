package org.globant.restaurant.model;

import jakarta.annotation.PostConstruct;
import java.util.UUID;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private UUID uuid;
    private String fantasyName;
    private String category;
    private String description;
    private String price;
    private Boolean available;

    @PostConstruct
    private void postConstruct() {
        this.fantasyName = fantasyName.toUpperCase();
        this.category = category.toUpperCase();
    }
}