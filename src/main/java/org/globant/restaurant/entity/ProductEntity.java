package org.globant.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    private Long id;
    private UUID uuid;
    private String name;
    private Category category;
    private String description;
    private String price;
    private Boolean available;
}

enum Category {
    HAMBURGERS_AND_HOTDOGS,
    CHICKEN,
    FISH,
    MEATS,
    DESSERTS,
    VEGAN_FOOD,
    KIDS_MEALS
}
