package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.BillingDetailsRepository;
import com.revature.spaceecobackend.dto.BillingDetailsDto;
import com.revature.spaceecobackend.exception.EmptyFields;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.Address;
import com.revature.spaceecobackend.model.BillingDetails;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BillingServiceTest {
  @Mock
  private BillingDetailsRepository billingDetailsRepo;

  @InjectMocks
  private BillingDetailsService billingDetailsService;

  @Spy
  ModelMapper modelMapper = new ModelMapper();


  private static Address address;
  private static BillingDetails billingDetails;
  private static BillingDetailsDto billingDetailsDto;


  @BeforeAll
  public static void init() {
    address = new Address(1, "123 Fake St", null, "Springfield", "Kentucky", "United States of America", "80000", "Solar System", "Earth");

    billingDetails = new BillingDetails(1, "MasterCard", 4519777777777777L, 123, "Homer Simpson", address);
    billingDetailsDto = new BillingDetailsDto("MasterCard", 4519777777777777L, 123, "Homer Simpson", address);

  }

  @Test
  void getBillingDetailById_Positive() {
    when(billingDetailsRepo.findById(1)).thenReturn(Optional.of(billingDetails));
    BillingDetails actual = billingDetailsService.getBillingDetailById(1);
    assertThat(actual).isEqualTo(billingDetails);
  }

  @Test
  void getBillingDetailsThatDoNotExist_negative() {
    Assertions.assertThrows(NotFound.class, () -> {
      billingDetailsService.deleteBillingDetails(1);
    });
  }

  @Test
  void createBillingDetail_Positive() throws EmptyFields {
    when(billingDetailsRepo.saveAndFlush(any(BillingDetails.class))).thenReturn(billingDetails);
    BillingDetails actual = billingDetailsService.createBillingDetail(billingDetailsDto);
    assertThat(actual).isEqualTo(billingDetails);
  }

  @Test
  void createBillingDetail_NegativeException() {
    BillingDetailsDto emptyDetails = new BillingDetailsDto();
    Assertions.assertThrows(EmptyFields.class, () -> {
      billingDetailsService.createBillingDetail(emptyDetails);
    });

  }

  @Test
  void updateBillingDetail_Positive() throws NotFound {
    when(billingDetailsRepo.findById(billingDetails.getId())).thenReturn(Optional.of(billingDetails));
    when(billingDetailsRepo.saveAndFlush(any(BillingDetails.class))).thenReturn(billingDetails);
    BillingDetails actual = billingDetailsService.updateBillingDetails(billingDetailsDto, billingDetails.getId());
    assertThat(actual).isEqualTo(billingDetails);
  }

  @Test
  void updateBillingDetail_NegativeException() {
    Assertions.assertThrows(NotFound.class, () -> {
      billingDetailsService.updateBillingDetails(billingDetailsDto, 500);
    });
  }

  @Test
  void deleteBillingDetail_Positive() throws NotFound {
    when(billingDetailsRepo.findById(billingDetails.getId())).thenReturn(Optional.of(billingDetails));
    assertThat(billingDetailsService.deleteBillingDetails(billingDetails.getId())).isTrue();
  }

  @Test
  void deleteBillingDetailsThatDoNotExist_negative() {
    Assertions.assertThrows(NotFound.class, () -> {
      billingDetailsService.deleteBillingDetails(billingDetails.getId());
    });
  }

}
