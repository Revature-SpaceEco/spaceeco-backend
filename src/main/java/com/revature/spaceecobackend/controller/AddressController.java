package com.revature.spaceecobackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.revature.spaceecobackend.model.Address;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController("/address")
public class AddressController {

    //add address service as private instance variable @Autowired

    //add users as private instance variable @Autowired
    /*
    @PostMapping
    public ResponseEntity<?> createAddress(@RequestBody Address address) throws JsonProcessingException{ Create
        return addressService.createAddress;

    }*/
    /*
    @GetMapping("/{id}")
    public Optional<Address> getAddressByUserId(@PathVariable String id) { //change name if naming convention changes //Response
        return addressService.getAddressByUserId(Long.parseLong(id));
    }*/
    /*
    @DeleteMapping("/{id}")
    public Boolean deleteAddressById(@PathVariable String id){ Delete
        return addressService.deleteAddress(Long.parseLong(id));
    }*/
    /*
    @PutMapping
    public Address updateAddress(@PathVariable String id,
                                 @RequestBody Address address){ Update
        return addressService.updateAddressByUserId(Long.parseLong(id), address);
    } */

        /*

    @PatchMapping
    public Address updateAddress2(@PathVariable String id,
                                 @RequestBody Address address){ Update
        return addressService.updateAddressByUserId2(Long.parseLong(id));
    } */
}
