package com.revature.spaceecobackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && active == user.active && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(userRole, user.userRole) && Objects.equals(primaryAddressId, user.primaryAddressId) && Objects.equals(primaryBillingId, user.primaryBillingId) && Objects.equals(imageUrl, user.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, firstName, lastName, userRole, primaryAddressId, primaryBillingId, imageUrl, active);
    }
}
