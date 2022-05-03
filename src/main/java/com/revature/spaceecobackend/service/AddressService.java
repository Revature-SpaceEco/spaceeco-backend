package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.AddressRepository;
import com.revature.spaceecobackend.dao.UserRepository;
import com.revature.spaceecobackend.dto.AddressDTO;
import com.revature.spaceecobackend.exception.NotFound;
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
  public Address createAddressOrder(AddressDTO addressDTO){
    Address address = modelMapper.map(addressDTO, Address.class);
    return addressRepository.saveAndFlush(address);
  }

  @Transactional
  public Address createAddress(int userId, AddressDTO addressDTO) throws NotFound {
    Address createdAddress = createAddressOrder(addressDTO);
    Optional<User> user = userRepository.findById(userId);
    if(user.isPresent()){
      user.get().setPrimaryAddressId(createdAddress);
    }else{
      throw new NotFound("User with Id " + userId + " doesn't exist");
    }
    return createdAddress;
  }

  @Transactional
  public Address getAddressByUserId(int userId) throws NotFound {
    Optional<User> user = userRepository.findById(userId);
    if(user.isPresent()){
      return user.get().getPrimaryAddressId();
    }else{
      throw new NotFound("User with Id " + userId + " does not exist");
    }
  }

  @Transactional
  public Address updateAddressByUserId(int id, AddressDTO newAddress) throws NotFound {
    Optional<User> user = userRepository.findById(id);
    if(user.isPresent()) {
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
    }else{
      throw new NotFound("User with ID "+ id + " does not exist");
    }
  }
}