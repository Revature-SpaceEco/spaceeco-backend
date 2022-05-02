package com.revature.spaceecobackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private int id;

  @Column(name = "user_username", unique = true, nullable = false)
  private String username;

  @Column(name = "user_password", nullable = false)
  private String password;

  @Column(name = "user_email", unique = true, nullable = false)
  private String email;

  @Column(name = "user_first_name")
  private String firstName;

  @Column(name = "user_last_name")
  private String lastName;

  @ManyToOne
  @JoinColumn(name = "role_user_id")
  private UserRole userRole;

  @OneToOne
  @JoinColumn(name = "primary_address_id")
  private Address primaryAddressId;

  @OneToOne
  @JoinColumn(name = "primary_billing_id")
  private BillingDetails primaryBillingId;

  @Column(name = "profile_image")
  private String imageUrl;

  @Column(name = "is_user_active")
  private boolean active;

  @Column(name = "user_secret")
  private String secret;

}
