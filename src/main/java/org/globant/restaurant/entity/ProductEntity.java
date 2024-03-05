package org.globant.restaurant.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private UUID uuid;

    @NotNull
    @Column(nullable = false)
    private String fantasyName;

    @NotNull
    @Column(nullable = false)
    private Category category;

    @Column(columnDefinition = "TEXT(511) NOT NULL", nullable = false)
    private String description;

    @NotEmpty
    @Column(nullable = false)
    private Double price;

    @NotNull
    @Column(nullable = false)
    private boolean available;

    enum Category {
        HAMBURGERS_AND_HOTDOGS,
        CHICKEN,
        FISH,
        MEATS,
        DESSERTS,
        VEGAN_FOOD,
        KIDS_MEALS
    }
}
