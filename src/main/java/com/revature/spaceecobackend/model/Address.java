package com.revature.spaceecobackend.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "primary_address_id" )
  private int id;


  @Column(nullable = false)
  private String addressLineOne;

  @Column
  private String addressLineTwo; //can be null

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private String state;

  @Column(nullable = false)
  private String country;

  @Column(nullable = false)
  private String zip;

  @Column(nullable = false)
  private String solarSystem;

  @Column(nullable = false)
  private String planet;


}
