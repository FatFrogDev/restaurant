package org.globant.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private UUID uuid;

    @Column(nullable = false)
    private LocalDateTime creationDateTime;

    @ManyToOne
    private ClientEntity client;

    @OneToMany
    private List<ProductEntity> product;

    private int quantity;

    private String extraInformation;

    private double subTotal;

    private double tax;

    private double granTotal;

    private boolean delivered;

    private LocalDateTime deliveryDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEntity that)) return false;
        return quantity == that.quantity && Double.compare(subTotal, that.subTotal) == 0
                && Double.compare(tax, that.tax) == 0 && Double.compare(granTotal, that.granTotal) == 0
                && delivered == that.delivered && Objects.equals(id, that.id) && Objects.equals(uuid, that.uuid)
                && Objects.equals(creationDateTime, that.creationDateTime) && Objects.equals(client, that.client)
                && Objects.equals(product, that.product) && Objects.equals(extraInformation, that.extraInformation)
                && Objects.equals(deliveryDate, that.deliveryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, creationDateTime, client, product, quantity, extraInformation, subTotal, tax, granTotal, delivered, deliveryDate);
    }

}
