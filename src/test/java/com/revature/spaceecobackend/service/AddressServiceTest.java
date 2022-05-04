package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.AddressRepository;
import com.revature.spaceecobackend.dao.UserRepository;
import com.revature.spaceecobackend.dto.AddressDTO;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.Address;
import com.revature.spaceecobackend.model.BillingDetails;
import com.revature.spaceecobackend.model.User;
import com.revature.spaceecobackend.model.UserRole;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

  @Mock
  private AddressRepository addressRepository;

  @InjectMocks
  private AddressService addressService;

  @Spy
  ModelMapper modelMapper = new ModelMapper();

  @Mock
  private UserRepository userRepository;

  private static Address address;
  private static User user;
  private static AddressDTO addressDTO;
  private static UserRole ur;
  private static BillingDetails bd;

  @BeforeAll
  public static void init() {
    ur = new UserRole(2, "seller");
    bd = new BillingDetails();
    address = new Address(1,"234 Finch Avenue","111","Toronto","ON","M8I 8L9","Canada","Earth","Milky Way");
    user = new User(1, "username", "password", "123@gmail.com", "John", "Doe", ur, address, bd, "www.image.com", true, "secret");
    addressDTO = new AddressDTO(1,"234 Finch Avenue","111","Toronto","ON","Canada","M8I 8L9","Milky Way","Earth");
  }

  @Test
  void createAddressTest() throws NotFound {
    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    when(addressRepository.saveAndFlush(any(Address.class))).thenReturn(address);
    Address actual = addressService.createAddress(1, addressDTO);
    assertThat(actual).isEqualTo(address);
  }

  @Test
  void createAddress_UserNotFound(){
    Assertions.assertThrows(NotFound.class,()->{
      Address address = addressService.createAddress(1,addressDTO);
    });
  }

  @Test
  void createAddressOrderTest() {
    when(addressRepository.saveAndFlush(any(Address.class))).thenReturn(address);
    Address actual = addressService.createAddressOrder(addressDTO);
    AssertionsForClassTypes.assertThat(actual).isEqualTo(address);
  }

  @Test
  void getAddressByUserIdTest() throws NotFound {
    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    Address actual = addressService.getAddressByUserId(1);
    assertThat(actual).isEqualTo(address);
  }

  @Test
  void getAddressByUserId_UserNotFound(){
    Assertions.assertThrows(NotFound.class,()->{
      Address address = addressService.getAddressByUserId(1);
    });
  }

  @Test
   void updateAddressTestByUserId() throws NotFound {
    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    when(addressRepository.save(address)).thenReturn(address);
    Address actual = addressService.updateAddressByUserId(1, addressDTO);
    assertThat(actual).isEqualTo(address);
  }
  @Test
  void udpateAddress_UserNotFound(){
    Assertions.assertThrows(NotFound.class,()->{
      Address address = addressService.updateAddressByUserId(1,addressDTO);
    });
  }
}
