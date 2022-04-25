package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.BillingDetailsDto;
import com.revature.spaceecobackend.dto.PaymentDto;
import com.revature.spaceecobackend.exception.EmptyFields;
import com.revature.spaceecobackend.model.Address;
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

    @BeforeAll
    public static void init() {
        billingDetailsDto = new BillingDetailsDto("Mastercard", 1002004587406874L, 123, "Katara WaterTribe", address);
        billingDetailsDto2 = new BillingDetailsDto("Visa", 6007004587406874L, 456, "Katara WaterTribe", address);
        address = new Address(1, "809 Frozen Water Way", null, "Southern", "Water Tribe", "Elements", "10000", "Spirit World", "Earth");
        paymentDto = new PaymentDto(1, "pending", billingDetailsDto);
    }

    // getPaymentById
    @Test
    void getPaymentById_postive() {
        when(paymentService.getPaymentById(1)).thenReturn(paymentDto);
        ResponseEntity<?> response = paymentController.getPaymentById(1);
        PaymentDto actual = (PaymentDto) response.getBody();

        assertThat(actual).isEqualTo(paymentDto);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void getPaymentById_negative() {
        when(paymentService.getPaymentById(500)).thenReturn(null);
        ResponseEntity<?> response = paymentController.getPaymentById(500);
        int expectedStatus = 404;
        assertThat(response.getStatusCodeValue()).isEqualTo(expectedStatus);
    }

    // CreatePayment
    @Test
    void createNewPayment_positive() throws EmptyFields {
        when(paymentService.createPayment(paymentDto)).thenReturn(paymentDto);
        ResponseEntity<?> response = paymentController.createPayment(paymentDto);
        PaymentDto actual = (PaymentDto) response.getBody();

        assertThat(actual).isEqualTo(paymentDto);
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
    void updatePaymentStatus_postive() throws PaymentNotFound {
        PaymentDto updated = new PaymentDto(1, "approved", billingDetailsDto);
        when(paymentService.updatePaymentStatus(paymentDto)).thenReturn(updated);
        ResponseEntity<?> response = paymentController.updatePaymentStatus(paymentDto);
        PaymentDto actual = (PaymentDto) response.getBody();

        assertThat(actual).isEqualTo(updated);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void updatePaymentStatus_negative() throws PaymentNotFound {
        when(paymentService.updatePaymentStatus(any(PaymentDto.class))).thenThrow(PaymentNotFound.class);
        ResponseEntity<?> response = paymentController.updatePaymentStatus(paymentDto);
        int expected = 404;
        assertThat(response.getStatusCodeValue()).isEqualTo(expected);
    }


    @Test
    void editPaymentDetails_postive() throws PaymentNotFound {
        PaymentDto edited = new PaymentDto(1, "pending", billingDetailsDto2);
        when(paymentService.editPaymentDetails(paymentDto)).thenReturn(edited);
        ResponseEntity<?> response = paymentController.editPaymentDetails(paymentDto);
        assertThat(response.getBody()).isEqualTo(edited);
    }

    @Test
    void editPaymentDetails_negative() throws PaymentNotFound {
        when(paymentService.editPaymentDetails(paymentDto)).thenThrow(PaymentNotFound.class);
        ResponseEntity<?> response = paymentController.editPaymentDetails(paymentDto);
        int expected = 404;
        assertThat(response.getStatusCodeValue()).isEqualTo(expected);
    }

    // DeletePayment
    @Test
    void deletePaymentDetails_postive() throws PaymentNotFound {
        when(paymentService.deletePayment(1)).thenReturn(true);
        ResponseEntity<?> response = paymentController.deletePayment(1);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }


    @Test
    void deletePaymentDetails_PaymentNotFound() throws PaymentNotFound {
        when(paymentService.deletePayment(1)).thenThrow(PaymentNotFound.class);
        ResponseEntity<?> response = paymentController.deletePayment(1);
        int expected = 404;
        assertThat(response.getStatusCodeValue()).isEqualTo(expected);
    }
}
