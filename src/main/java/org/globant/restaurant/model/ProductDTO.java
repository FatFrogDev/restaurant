package org.globant.restaurant.model;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName.toUpperCase();
    }

    public void setCategory(String category) {
        this.category = category.toUpperCase();
    }
}