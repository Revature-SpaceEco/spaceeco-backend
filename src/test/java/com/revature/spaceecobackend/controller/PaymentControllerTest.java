package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.BillingDetailsDto;
import com.revature.spaceecobackend.dto.PaymentDto;
import com.revature.spaceecobackend.exception.EmptyFields;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.Address;
import com.revature.spaceecobackend.model.BillingDetails;
import com.revature.spaceecobackend.model.Payment;
import com.revature.spaceecobackend.service.PaymentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    PaymentController paymentController;

    private static PaymentDto paymentDto;
    private static BillingDetailsDto billingDetailsDto;
    private static Address address;
    private static BillingDetailsDto billingDetailsDto2;
    private static Payment payment;
    private static BillingDetails billingDetails;
    private static BillingDetails billingDetails2;

    @BeforeAll
    public static void init() {
        address = new Address(1, "809 Frozen Water Way", null, "Southern", "Water Tribe", "Elements", "10000", "Spirit World", "Earth");
        billingDetailsDto = new BillingDetailsDto("Mastercard", 1002004587406874L, 123, "Katara WaterTribe", address);
        billingDetailsDto2 = new BillingDetailsDto("Visa", 6007004587406874L, 456, "Katara WaterTribe", address);
        paymentDto = new PaymentDto(1, billingDetailsDto, "pending");
        billingDetails = new BillingDetails(1, "Visa", 6007004587406874L, 456, "Katara WaterTribe", address);
        payment = new Payment(1, billingDetails, "pending");
        billingDetails2 = new BillingDetails(1, "Mastercard", 6007004587406874L, 456, "Katara WaterTribe", address);
    }

    // getPaymentById
    @Test
    void getPaymentById_postive() throws NotFound {
        when(paymentService.getPaymentById(1)).thenReturn(payment);
        ResponseEntity<?> response = paymentController.getPaymentById(1);
        Payment actual = (Payment) response.getBody();

        assertThat(actual).isEqualTo(payment);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void getPaymentById_negative() throws NotFound {
        when(paymentService.getPaymentById(500)).thenThrow(NotFound.class);
        ResponseEntity<?> response = paymentController.getPaymentById(500);
        int expectedStatus = 404;
        assertThat(response.getStatusCodeValue()).isEqualTo(expectedStatus);
    }

    // CreatePayment
    @Test
    void createNewPayment_positive() throws EmptyFields {
        when(paymentService.createPayment(paymentDto)).thenReturn(payment);
        ResponseEntity<?> response = paymentController.createPayment(paymentDto);
        Payment actual = (Payment) response.getBody();

        assertThat(actual).isEqualTo(payment);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void createNewPayment_negative() throws EmptyFields {
        when(paymentService.createPayment(paymentDto)).thenThrow(EmptyFields.class);
        ResponseEntity<?> response = paymentController.createPayment(paymentDto);
        int expectedStatus = 400;
        int actualStatus = response.getStatusCodeValue();
        assertThat(actualStatus).isEqualTo(expectedStatus);
    }

    // UpdatePaymentStatus
    @Test
    void updatePaymentStatus_postive() throws NotFound {
        Payment updated = new Payment(1, billingDetails, "approved");
        when(paymentService.updatePayment(paymentDto)).thenReturn(updated);
        ResponseEntity<?> response = paymentController.updatePaymentStatus(paymentDto);
        Payment actual = (Payment) response.getBody();

        assertThat(actual).isEqualTo(updated);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void updatePaymentStatus_negative() throws NotFound {
        when(paymentService.updatePayment(any(PaymentDto.class))).thenThrow(NotFound.class);
        ResponseEntity<?> response = paymentController.updatePaymentStatus(paymentDto);
        int expected = 404;
        assertThat(response.getStatusCodeValue()).isEqualTo(expected);
    }


    @Test
    void editPaymentDetails_postive() throws NotFound {
        Payment edited = new Payment(1,  billingDetails2, "pending");
        when(paymentService.updatePayment(paymentDto)).thenReturn(edited);
        ResponseEntity<?> response = paymentController.editPaymentDetails(paymentDto);
        assertThat(response.getBody()).isEqualTo(edited);
    }

    @Test
    void editPaymentDetails_negative() throws NotFound {
        when(paymentService.updatePayment(paymentDto)).thenThrow(NotFound.class);
        ResponseEntity<?> response = paymentController.editPaymentDetails(paymentDto);
        int expected = 404;
        assertThat(response.getStatusCodeValue()).isEqualTo(expected);
    }

    // DeletePayment
    @Test
    void deletePaymentDetails_postive() throws NotFound {
        when(paymentService.deletePaymentById(1)).thenReturn(true);
        ResponseEntity<?> response = paymentController.deletePayment(1);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }


    @Test
    void deletePaymentDetails_PaymentNotFound() throws NotFound {
        when(paymentService.deletePaymentById(1)).thenThrow(NotFound.class);
        ResponseEntity<?> response = paymentController.deletePayment(1);
        int expected = 404;
        assertThat(response.getStatusCodeValue()).isEqualTo(expected);
    }
}
