package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.AddressDTO;
import com.revature.spaceecobackend.model.Address;
import com.revature.spaceecobackend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AddressController {

  @Autowired
  AddressService addressService;

  @PostMapping("/address")
  public Address createAddressOrder(@RequestBody AddressDTO addressDTO) {
    return addressService.createAddressOrder(addressDTO);
  }

  @PostMapping("/users/{userId}/address")
  public Address createAddress(@PathVariable String userId,
                               @RequestBody AddressDTO addressDTO) {

    return addressService.createAddress(Integer.parseInt(userId), addressDTO);
  }

  @GetMapping("/users/{userId}/address")
  public Address getAddressByUserId(@PathVariable String userId) {
    return addressService.getAddressByUserId(Integer.parseInt(userId));
  }

  @PutMapping("/users/{userId}/address")
  public Address updateAddressByUserId(@PathVariable String userId,
                                       @RequestBody AddressDTO address) {
    return addressService.updateAddressByUserId(Integer.parseInt(userId), address);
  }

}
