package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.AddressRepository;
import com.revature.spaceecobackend.dao.UserRepository;
import com.revature.spaceecobackend.model.Address;
import com.revature.spaceecobackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AddressService{

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    @Transactional
    public Address getAddressByUserId(int userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.get().getPrimaryAddressId();

    }

    public Boolean deleteAddressById(int id) {
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
