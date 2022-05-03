package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.AddressDTO;
import com.revature.spaceecobackend.model.Address;
import com.revature.spaceecobackend.model.BillingDetails;
import com.revature.spaceecobackend.model.User;
import com.revature.spaceecobackend.model.UserRole;
import com.revature.spaceecobackend.service.AddressService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    private static User buyer;
    private static UserRole userRole;
    private static Address address;
    private static AddressDTO addressDTO;
    private static BillingDetails billingDetails;

    @BeforeAll
    private static void init() {
        userRole = new UserRole(1, "buyer");
        buyer = new User(1, "test", "password", "email@email.com", "test", "test", userRole, address, billingDetails, "www.image.com/1", true, "secret");
        address = new Address(1, "9194 North College Ave", "512 South Pennsylvania St", "Grand Haven", "MI", "United States", "49417", "solar system", "earth");
        billingDetails = new BillingDetails();
        addressDTO = new AddressDTO(1, "9194 North College Ave", "512 South Pennsylvania St", "Grand Haven", "MI", "United States", "49417", "solar system", "earth");

    }

    @Test
     void getAddressByIdTest() throws Exception{
        when(addressService.getAddressByUserId(buyer.getId())).thenReturn(address);
        Address response = addressController.getAddressByUserId("1");
        assertThat(response).isEqualTo(address);
    }


    @Test
    public void createAddressTest() throws Exception {
        when(addressService.createAddress(1, addressDTO)).thenReturn(address);
        Address response = addressController.createAddress("1", addressDTO);
        assertThat(address).isEqualTo(response);
    }

    @Test
    public void createAddressOrderTest() throws Exception {
        when(addressService.createAddressOrder(addressDTO)).thenReturn(address);
        Address response = addressController.createAddressOrder(addressDTO);
        assertThat(address).isEqualTo(response);
    }

    @Test
    void updateAddressByUserIdTest() throws Exception {
        when(addressService.updateAddressByUserId(1, addressDTO)).thenReturn(address);
        Address response = addressController.updateAddressByUserId("1", addressDTO);
        assertThat(address).isEqualTo(response);
    }

}
