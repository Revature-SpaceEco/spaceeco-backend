package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.AddressRepository;
import com.revature.spaceecobackend.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService{

    @Autowired
    AddressRepository addressRepository;

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

  /*  public Optional<Address> getAddressByUserId(int id) {//Fix later
        return userRepository.findAddressByUserId(id);
    }*/

    public Boolean deleteAddress(int id) {
        addressRepository.deleteById(id);
        return !addressRepository.existsById(id);
    }

   /* public Address updateAddressByUserId(int id, Address address) {
        return addressRepository.findById(id, address);
    }

    public Address updateAddressByUserId2(Address address) {
        return addressRepository.save(address);
    }*/
}
