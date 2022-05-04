package com.revature.spaceecobackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private int id;

    private String addressLineOne;

    private String addressLineTwo; //can be null

    private String city;

    private String state;

    private String country;

    private String zip;

    private String solarSystem;

    private String planet;
}
