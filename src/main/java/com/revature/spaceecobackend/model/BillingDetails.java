package com.revature.spaceecobackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @Column(name = "billing_card_type", nullable = false)
  private String cardType;

  @Column(name = "billing_card_number", nullable = false)
  private long cardNumber;

  @Column(name = "billing_security_number", nullable = false)
  private int securityNumber;

  @Column(name = "billing_name", nullable = false)
  private String name;

  @JoinColumn(name = "billing_address")
  @ManyToOne
  private Address address;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BillingDetails that = (BillingDetails) o;
    return id == that.id && cardNumber == that.cardNumber && securityNumber == that.securityNumber && Objects.equals(cardType, that.cardType) && Objects.equals(name, that.name) && Objects.equals(address, that.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cardType, cardNumber, securityNumber, name, address);
  }

  @Override
  public String toString() {
    return "BillingDetails{" +
            "id=" + id +
            ", cardType='" + cardType + '\'' +
            ", cardNumber=" + cardNumber +
            ", securityNumber=" + securityNumber +
            ", name='" + name + '\'' +
            ", address=" + address +
            '}';
  }
}