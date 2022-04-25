package com.revature.spaceecobackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="payments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "billing_details")
    @OneToOne
    private BillingDetails billingDetails;

    @Column(name="payment_status", nullable=false,columnDefinition = "varchar(100) default 'Pending'")
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id && Objects.equals(billingDetails, payment.billingDetails) && Objects.equals(status, payment.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, billingDetails, status);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", billingDetails=" + billingDetails +
                ", status='" + status + '\'' +
                '}';
    }
}
