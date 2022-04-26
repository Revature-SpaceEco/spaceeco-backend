package com.revature.spaceecobackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.spaceecobackend.dto.AddressDTO;
import com.revature.spaceecobackend.model.Address;
import com.revature.spaceecobackend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
@RequestMapping("/users/{userId}")
public class AddressController {

  //add users as private instance variable @Autowired

  @Autowired
  AddressService addressService;
//
//     @PostMapping
//     public Address createAddress(@RequestBody Address address) throws JsonProcessingException{ //Create
//         return addressService.createAddress(address);
//     }

  @PostMapping("/address")
  public Address createAddress(@PathVariable String userId,
                               @RequestBody AddressDTO addressDTO) throws JsonProcessingException { //Create

    return addressService.createAddress(Integer.parseInt(userId), addressDTO);
  }

  @GetMapping("/address")
  public Address getAddressByUserId(@PathVariable String userId) { //change name if naming convention changes //Response
    return addressService.getAddressByUserId(Integer.parseInt(userId));
  }

//     @DeleteMapping("/{id}")
//     public Boolean deleteAddressById(@PathVariable String id){ //Delete
//         return addressService.deleteAddressById(Integer.parseInt(id));
//     }

  @PutMapping("/address")
  public Address updateAddressByUserId(@PathVariable String userId,
                                       @RequestBody AddressDTO address) { //Update
    return addressService.updateAddressByUserId(Integer.parseInt(userId), address);
  }

}