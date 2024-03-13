package org.globant.restaurant.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="products")
@OnDelete(action = OnDeleteAction.CASCADE)
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uuid;

    @NotNull
    @Column(nullable = false, unique = true)
    private String fantasyName;

    @NotNull
    @Column(nullable = false)
    private Category category;

    @Column(columnDefinition = "TEXT(511) NOT NULL", nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @NotNull
    @Column(nullable = false)
    private boolean available;

    public enum Category {
        HAMBURGERS_AND_HOTDOGS,
        CHICKEN,
        FISH,
        MEATS,
        DESSERTS,
        VEGAN_FOOD,
        KIDS_MEALS
    }
}
