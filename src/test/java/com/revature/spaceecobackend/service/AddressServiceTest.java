package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.AddressRepository;
import com.revature.spaceecobackend.dao.UserRepository;
import com.revature.spaceecobackend.dto.AddressDTO;
import com.revature.spaceecobackend.model.Address;
import com.revature.spaceecobackend.model.BillingDetails;
import com.revature.spaceecobackend.model.User;
import com.revature.spaceecobackend.model.UserRole;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

  @Mock
  private AddressRepository addressRepository;

  @InjectMocks
  private AddressService addressService;

  @Spy
  ModelMapper modelMapper = new ModelMapper();

  @Mock
  private UserRepository userRepository;

  @Test
    public void createAddressTest() {
    Address address = new Address();
    address.setAddressLineOne("234 Finch Avenue");
    address.setAddressLineTwo("111");
    address.setCity("Toronto");
    address.setState("ON");
    address.setZip("M8I 8L9");
    address.setCountry("Canada");
    address.setPlanet("Earth");
    address.setSolarSystem("Milky Way");

    UserRole ur = new UserRole(2, "seller");
    BillingDetails bd = new BillingDetails();

    User user = new User(1, "username", "password", "123@gmail.com", "John", "Doe", ur, address, bd, "www.image.com", true);
    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    when(addressRepository.saveAndFlush(address)).thenReturn(address);
    Address actual = addressService.createAddress(1, new AddressDTO("234 Finch Avenue","111","Toronto","ON","Canada","M8I 8L9","Milky Way","Earth"));
    assertThat(actual).isEqualTo(address);
  }
  @Test
  public void createAddressOrderTest() {
    Address address = new Address();
    address.setAddressLineOne("234 Finch Avenue");
    address.setAddressLineTwo("111");
    address.setCity("Toronto");
    address.setState("ON");
    address.setZip("M8I 8L9");
    address.setCountry("Canada");
    address.setPlanet("Earth");
    address.setSolarSystem("Milky Way");

    UserRole ur = new UserRole(2, "seller");
    BillingDetails bd = new BillingDetails();
    User user = new User(1, "username", "password", "123@gmail.com", "John", "Doe", ur, address, bd, "www.image.com", true);

    when(addressRepository.save(address)).thenReturn(address);
    Address actual = addressService.createAddressOrder(address);
    AssertionsForClassTypes.assertThat(actual).isEqualTo(address);
  }

  @Test
  public void getAddressByUserIdTest() {
    Address expect = new Address();

    expect.setAddressLineOne("234 Finch Avenue");
    expect.setCity("Toronto");
    expect.setState("ON");
    expect.setZip("M8I 8L9");
    expect.setCountry("Canada");
    expect.setPlanet("Earth");
    expect.setSolarSystem("Milky Way");
    UserRole ur = new UserRole(2, "seller");
    BillingDetails bd = new BillingDetails();

    User user = new User(1, "username", "password", "123@gmail.com", "John", "Doe", ur, expect, bd, "www.image.com", true);

    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    Address actual = addressService.getAddressByUserId(1);
    assertThat(actual).isEqualTo(expect);
  }

  @Test
  public void updateAddressTestByUserId() {
    Address address = new Address();
    address.setAddressLineOne("234 Finch Avenue");
    address.setAddressLineTwo("111");
    address.setCity("Toronto");
    address.setState("ON");
    address.setZip("M8I 8L9");
    address.setCountry("Canada");
    address.setPlanet("Earth");
    address.setSolarSystem("Milky Way");

    UserRole ur = new UserRole(2, "seller");
    BillingDetails bd = new BillingDetails();

    User user = new User(1, "username", "password", "123@gmail.com", "John", "Doe", ur, address, bd, "www.image.com", true);

    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    when(addressRepository.save(address)).thenReturn(address);
    Address actual = addressService.updateAddressByUserId(1, new AddressDTO("234 Finch Avenue","111","Toronto","ON","Canada","M8I 8L9","Earth","Milky Way"));
    assertThat(actual).isEqualTo(address);
  }

}
