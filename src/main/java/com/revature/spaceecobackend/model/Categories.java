package com.revature.spaceecobackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Categories {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int categoryId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Categories that = (Categories) o;
    return categoryId == that.categoryId && Objects.equals(categoryName, that.categoryName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(categoryId, categoryName);
  }

  @Column
  private String categoryName;
}