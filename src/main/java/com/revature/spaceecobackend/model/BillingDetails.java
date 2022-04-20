package com.revature.spaceecobackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "billing_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BillingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="billing_card_type", nullable = false)
    private String billingCardType;

    @Column(name="billing_card_number", nullable = false)
    private long billingCardNumber;

    @Column(name = "billing_security_number", nullable = false)
    private int billingSecurityNumber;

    @Column(name = "billing_name", nullable = false)
    private String billingName;

    @JoinColumn(name="billing_address")
    @ManyToOne
    private Address billingAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillingDetails that = (BillingDetails) o;
        return id == that.id && billingCardNumber == that.billingCardNumber && billingSecurityNumber == that.billingSecurityNumber && Objects.equals(billingCardType, that.billingCardType) && Objects.equals(billingName, that.billingName) && Objects.equals(billingAddress, that.billingAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, billingCardType, billingCardNumber, billingSecurityNumber, billingName, billingAddress);
    }

    @Override
    public String toString() {
        return "BillingDetails{" +
                "id=" + id +
                ", billingCardType='" + billingCardType + '\'' +
                ", billingCardNumber=" + billingCardNumber +
                ", billingSecurityNumber=" + billingSecurityNumber +
                ", billingName='" + billingName + '\'' +
                ", billingAddress=" + billingAddress +
                '}';
    }
}