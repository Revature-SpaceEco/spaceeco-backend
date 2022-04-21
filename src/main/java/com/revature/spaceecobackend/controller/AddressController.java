 package com.revature.spaceecobackend.controller;

 import com.fasterxml.jackson.core.JsonProcessingException;

 import com.revature.spaceecobackend.model.Address;
 import com.revature.spaceecobackend.service.AddressService;
 // import com.revature.spaceecobackend.service.AddressServiceInterface;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

 import java.util.Optional;
 @RestController("/address")
 public class AddressController {

     //add address service as private instance variable @Autowired

     //add users as private instance variable @Autowired

     @Autowired
     AddressService addressService;

     @PostMapping
     public Address createAddress(@RequestBody Address address) throws JsonProcessingException{ //Create
         return addressService.createAddress(address);
     }

     @GetMapping("/{userId}")
     public Address getAddressByUserId(@PathVariable String userId) { //change name if naming convention changes //Response
         return addressService.getAddressByUserId(Integer.parseInt(userId));
     }

     @DeleteMapping("/{id}")
     public Boolean deleteAddressById(@PathVariable String id){ //Delete
         return addressService.deleteAddressById(Integer.parseInt(id));
     }

    /* @PutMapping
     public Address updateAddress(@PathVariable String id,
                                  @RequestBody Address address){ //Update
         return addressService.updateAddressByUserId(Integer.parseInt(id), address);
     }


     @PatchMapping
     public Address updateAddress2(@PathVariable String id,
                                   @RequestBody Address address){ //
         return addressService.updateAddressByUserId2(address);
     }*/
 }