package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.BillingDetailsRepository;
import com.revature.spaceecobackend.model.Address;
import com.revature.spaceecobackend.model.BillingDetails;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BillingServiceTest {
  @Mock
  private BillingDetailsRepository billingDetailsRepo;

  @InjectMocks
  private BillingDetailsService billingDetailsService;


  private static Address address;
  private static BillingDetails billingDetails;


  @BeforeAll
  public static void init(){
    address = new Address(1, "123 Fake St", null, "Springfield", "Kentucky", "United States of America", "80000", "Solar System", "Earth");

    billingDetails = new BillingDetails(1,"MasterCard",4519777777777777L,123,"Homer Simpson",address);
  }

  @Test
  void getBillingDetailById_Positive() {
    when(billingDetailsRepo.findById(1)).thenReturn(Optional.of(billingDetails));
    
  }

  @Test
  void createBillingDetail_Positive(){
//      when(billingDetailsRepo.saveAllAndFlush(any(BillingDetails.class))).thenReturn(billingDetails);
  }

  @Test
  void createBillingDetail_NegativeException(){

  }

  @Test
  void updateBillingDetail_Positive(){
//    when(billingDetailsRepo.)
  }

  @Test
  void updateBillingDetail_NegativeException(){

  }

  @Test
  void deleteBillingDetail_Positive(){

  }

}
