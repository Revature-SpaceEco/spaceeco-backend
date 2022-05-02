package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.AddressRepository;
import com.revature.spaceecobackend.dao.UserRepository;
import com.revature.spaceecobackend.dto.AddressDTO;
import com.revature.spaceecobackend.model.Address;
import com.revature.spaceecobackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AddressService {

  @Autowired
  AddressRepository addressRepository;
  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  UserRepository userRepository;

  @Transactional
  public Address createAddressOrder(Address address){
    return addressRepository.save(address);
  }

  @Transactional
  public Address createAddress(int userId, AddressDTO addressDTO) {

    Address address = modelMapper.map(addressDTO, Address.class);
    Address createdAddress = addressRepository.saveAndFlush(address);
    Optional<User> user = userRepository.findById(userId);
    user.get().setPrimaryAddressId(createdAddress);
    return createdAddress;
  }

  @Transactional
  public Address getAddressByUserId(int userId) {
    Optional<User> user = userRepository.findById(userId);
    return user.get().getPrimaryAddressId();

  }

  @Transactional
  public Address updateAddressByUserId(int id, AddressDTO newAddress) {
    Optional<User> user = userRepository.findById(id);
    Address oldAddress = user.get().getPrimaryAddressId();

    oldAddress.setAddressLineOne(newAddress.getAddressLineOne());
    oldAddress.setAddressLineTwo(newAddress.getAddressLineTwo());
    oldAddress.setCity(newAddress.getCity());
    oldAddress.setState(newAddress.getState());
    oldAddress.setCountry(newAddress.getCountry());
    oldAddress.setZip(newAddress.getZip());
    oldAddress.setPlanet(newAddress.getPlanet());
    oldAddress.setSolarSystem(newAddress.getSolarSystem());
    return addressRepository.save(oldAddress);
  }


}