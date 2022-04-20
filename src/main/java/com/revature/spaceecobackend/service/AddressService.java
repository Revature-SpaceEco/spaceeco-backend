package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.AddressRepository;
import com.revature.spaceecobackend.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService implements AddressServiceInterface{

    @Autowired
    AddressRepository addressRepository;

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Optional<?> getAddress(int id) {
        return Optional.empty();
    }

    public Boolean deleteAddress(int id) {
        return null;
    }

    public AddressService updateAddressByUserId(int id, Address address) {
        return null;
    }

    public AddressService updateAddressByUserId2(int id, Address address) {
        return null;
    }
}
