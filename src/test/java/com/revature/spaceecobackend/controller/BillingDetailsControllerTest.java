package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.BillingDetailsDto;
import com.revature.spaceecobackend.exception.EmptyFields;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.Address;
import com.revature.spaceecobackend.model.BillingDetails;
import com.revature.spaceecobackend.service.BillingDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BillingDetailsControllerTest {

  @Mock
  private BillingDetailsService billingDetailsService;

  @InjectMocks
  BillingDetailsController billingController;

  private static BillingDetailsDto billingDetailsDto;
  private static Address address;
  private static BillingDetails billingDetails;

  @BeforeAll
  public static void init() {
    billingDetails = new BillingDetails(1, "Mastercard", 1002004587406874L, 123, "Katara WaterTribe", address);
    billingDetailsDto = new BillingDetailsDto("Mastercard", 1002004587406874L, 123, "Katara WaterTribe", address);
    address = new Address(1, "809 Frozen Water Way", null, "Southern", "Water Tribe", "Elements", "10000", "Spirit World", "Earth");
  }

  @Test
  void getBillingById_positive() {
    when(billingDetailsService.getBillingDetailById(1)).thenReturn(billingDetails);
    ResponseEntity<?> response = billingController.getBillingById(1);
    BillingDetails actual = (BillingDetails) response.getBody();

    Assertions.assertEquals(billingDetails, actual);
  }

  @Test
  void getBillingByIdDoesNotExist() {
    when(billingDetailsService.getBillingDetailById(1)).thenReturn(null);
    ResponseEntity<?> response = billingController.getBillingById(1);
    int statusCode = response.getStatusCodeValue();
    int expected = 404;

    Assertions.assertEquals(expected, statusCode);
  }

  @Test
  void updateBillingDetailsValidDetailsAndId() throws NotFound {
    when(billingDetailsService.updateBillingDetails(billingDetailsDto, 1)).thenReturn(billingDetails);
    ResponseEntity<?> response = billingController.updateBillingDetails(1, billingDetailsDto);

    BillingDetails actual = (BillingDetails) response.getBody();
    Assertions.assertEquals(billingDetails, actual);
  }

  @Test
  void updateBillingDetailsNotFound_negative() throws NotFound {
    when(billingDetailsService.updateBillingDetails(billingDetailsDto, 100)).thenThrow(new NotFound());
    ResponseEntity<?> response = billingController.updateBillingDetails(100, billingDetailsDto);
    int expected = 404;
    Assertions.assertEquals(expected, response.getStatusCodeValue());

  }

  @Test
  void addBillingDetailsValidBody() throws EmptyFields {
    when(billingDetailsService.createBillingDetail(billingDetailsDto)).thenReturn(billingDetails);
    ResponseEntity<?> response = billingController.addBillingDetails(billingDetailsDto);
    BillingDetails actual = (BillingDetails) response.getBody();

    Assertions.assertEquals(billingDetails, actual);
  }

  @Test
  void addBillingDetailsInvalidBody() throws EmptyFields {

    when(billingDetailsService.createBillingDetail(billingDetailsDto)).thenThrow(EmptyFields.class);
    ResponseEntity<?> response = billingController.addBillingDetails(billingDetailsDto);
    int actualStatusCode = response.getStatusCodeValue();
    int expectedStatusCode = 400;
    Assertions.assertEquals(expectedStatusCode, actualStatusCode);
  }

  @Test
  void deleteExistingBillingDetails_positive() throws NotFound {
    when(billingDetailsService.deleteBillingDetails(1)).thenReturn(true);
    ResponseEntity<?> response = billingController.deleteBillingDetails(1);
    int actualStatusCode = response.getStatusCodeValue();
    int expectedStatusCode = 200;
    Assertions.assertEquals(expectedStatusCode, actualStatusCode);
  }

  @Test
  void deleteNonExistentBillingDetails_negative() throws NotFound {
    doThrow(new NotFound("Billing Details not found.")).when(billingDetailsService).deleteBillingDetails(anyInt());

    ResponseEntity<?> response = billingController.deleteBillingDetails(1);
    int expectedStatus = 404;
    Assertions.assertEquals(expectedStatus, response.getStatusCodeValue());
  }
}
