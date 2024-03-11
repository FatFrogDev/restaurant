package org.globant.restaurant.entity;

import jakarta.annotation.PostConstruct;
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

    @Column(nullable = false)
    private UUID uuid;

    public void setUuid(UUID uuid) {
        this.uuid = UUID.randomUUID();
    }

    @NotNull
    @Column(nullable = false)
    private String fantasyname;

    @NotNull
    @Column(nullable = false)
    private Category category;

    @Column(columnDefinition = "TEXT(511) NOT NULL", nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

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

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", fantasyName='" + fantasyname + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", available=" + available +
                '}';
    }

}
