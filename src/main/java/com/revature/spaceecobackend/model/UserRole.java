package com.revature.spaceecobackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRole {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_role_id")
  private int id;

  @Column(name = "user_role")
  private String role;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserRole userRole = (UserRole) o;
    return id == userRole.id && Objects.equals(role, userRole.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, role);
  }
}