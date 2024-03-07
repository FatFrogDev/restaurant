package org.globant.restaurant.model;

import java.util.UUID;

public class ProductDTO {
    private UUID uuid;
    private String fantasyName;
    private String category;
    private String description;
    private String price;
    private Boolean available;

    public ProductDTO() {

    }

    public ProductDTO(UUID uuid, String fantasyName, String category, String description, String price, Boolean available) {
        this.uuid = uuid;
        this.fantasyName = fantasyName;
        this.category = category;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}