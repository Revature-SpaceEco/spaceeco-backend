package com.revature.spaceecobackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
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

    public AddressDTO(String addressLineOne, String addressLineTwo, String city, String state, String country, String zip, String solarSystem, String planet) {
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
        this.solarSystem = solarSystem;
        this.planet = planet;
    }
}
