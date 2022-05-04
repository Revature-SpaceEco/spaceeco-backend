package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.PaymentRepository;
import com.revature.spaceecobackend.dto.AddressDTO;
import com.revature.spaceecobackend.dto.BillingDetailsDto;
import com.revature.spaceecobackend.dto.PaymentDto;
import com.revature.spaceecobackend.exception.EmptyFields;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.Address;
import com.revature.spaceecobackend.model.BillingDetails;
import com.revature.spaceecobackend.model.Payment;
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
public class PaymentServiceTest {

  @Mock
  private PaymentRepository paymentRepository;

  @Mock
  private AddressService addressService;

  @Mock
  private BillingDetailsService billingDetailsService;
  
  @Spy
  ModelMapper modelMapper = new ModelMapper();

  @InjectMocks
  private PaymentService paymentService;

  private static BillingDetails billingDetails;
  private static BillingDetailsDto billingDetailsDto;
  private static Address address;
  private static Payment payment;
  private static PaymentDto paymentDto;
  private static PaymentDto approvedPaymentDto;
  private static Payment approvedPayment;


  @BeforeAll
  public static void init() {
    address = new Address(1, "123 Fake St", null, "Springfield", "Kentucky", "United States of America", "80000", "Solar System", "Earth");
    billingDetails = new BillingDetails(1, "MasterCard", 4519777777777777L, 123, "Homer Simpson", address);
    billingDetailsDto = new BillingDetailsDto("MasterCard", 4519777777777777L, 123, "Homer Simpson", address);
    payment = new Payment(1, billingDetails, "Pending");
    paymentDto = new PaymentDto(1, billingDetailsDto, "Pending");
    approvedPaymentDto = new PaymentDto(1, billingDetailsDto, "Approved");
    approvedPayment = new Payment(payment.getId(), payment.getBillingDetails(), paymentDto.getStatus());
  }

  @Test
  void getPaymentById_positive() throws NotFound {
    when(paymentRepository.findById(payment.getId())).thenReturn(Optional.of(payment));
    Payment actual = paymentService.getPaymentById(payment.getId());
    assertThat(actual).isEqualTo(payment);
  }

  @Test
  void getPaymentById_PaymentNotFound() {
    Assertions.assertThrows(NotFound.class, () -> {
      paymentService.getPaymentById(100);
    });
  }

  @Test
  void createPayment_positive() throws EmptyFields {

    AddressDTO addressDTO = modelMapper.map(paymentDto.getBillingDetails().getAddress(), AddressDTO.class);

    when(modelMapper.map(paymentDto.getBillingDetails().getAddress(), AddressDTO.class)).thenReturn(addressDTO);

    when(addressService.createAddressOrder(addressDTO)).thenReturn(address);

    when(billingDetailsService.createBillingDetail(paymentDto.getBillingDetails())).thenReturn(billingDetails);

    when(paymentRepository.saveAndFlush(any(Payment.class))).thenReturn(payment);

    Payment actual = paymentService.createPayment(paymentDto);
    assertThat(actual).isEqualTo(payment);
  }

  @Test
  void createPayment_BillingDetailsNotFound() {
    Assertions.assertThrows(EmptyFields.class, () -> {
      paymentService.createPayment(new PaymentDto());
    });
  }

  @Test
  void updatePayment_positive() throws NotFound {
    when(paymentRepository.findById(paymentDto.getId())).thenReturn(Optional.of(payment));

    when(paymentRepository.saveAndFlush(any(Payment.class))).thenReturn(payment);
    Payment actual = paymentService.updatePayment(paymentDto);
    assertThat(actual).isEqualTo(approvedPayment);
  }

  @Test
  void updatePayment_BillingDetailsNotFound() {
    PaymentDto paymentDtoEmpty = new PaymentDto();
    Assertions.assertThrows(NotFound.class, () -> {
      paymentService.updatePayment(paymentDtoEmpty);
    });
  }

  @Test
  void deletePayment_positive() throws NotFound {
    when(paymentRepository.findById(1)).thenReturn(Optional.of(payment));
    assertThat(paymentService.deletePaymentById(1)).isTrue();
  }

  @Test
  void deletePayment_PaymentNotFound() {
    Assertions.assertThrows(NotFound.class, () -> {
      paymentService.deletePaymentById(500);
    });
  }

}
