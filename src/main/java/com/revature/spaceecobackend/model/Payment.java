package com.revature.spaceecobackend.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="payments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "billing_address_id")
    @OneToOne
    private int billingId;

    @Column(name="payment_type", nullable=false)
    private String type;

    @Column(name="payment_status", nullable=false)
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return id == payment.id && billingId == payment.billingId && Objects.equals(type, payment.type) && Objects.equals(status, payment.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, billingId, type, status);
    }
}
