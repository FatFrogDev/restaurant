package org.globant.restaurant.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name="clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "document", unique = true, updatable = false)
    private String document;

    @Column(name = "name")
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Override
    public String toString() {
        return "ClientEntity{" +
                "uuid=" + uuid +
                ", document='" + document + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientEntity that = (ClientEntity) o;

        if (!Objects.equals(uuid, that.uuid)) return false;
        if (!Objects.equals(document, that.document)) return false;
        if (!Objects.equals(fullName, that.fullName)) return false;
        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(phone, that.phone)) return false;
        return Objects.equals(deliveryAddress, that.deliveryAddress);
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (document != null ? document.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (deliveryAddress != null ? deliveryAddress.hashCode() : 0);
        return result;
    }
}
