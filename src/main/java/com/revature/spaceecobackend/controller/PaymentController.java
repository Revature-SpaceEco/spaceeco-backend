package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.PaymentDto;
import com.revature.spaceecobackend.exception.EmptyFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payments")
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    // getPaymentById
    @GetMapping("/{payment_id}")
    public ResponseEntity<?> getPaymentById(@PathVariable("payment_id") int id) {

        PaymentDto payment = paymentService.getPaymentById(id);
        if(payment != null) {
            return ResponseEntity.ok().body(payment);
        }
        return ResponseEntity.notFound().build();
    }

    // createPayment
    @PostMapping("/")
    public ResponseEntity<?> createPayment(@RequestBody PaymentDto paymentDto) {
      try {
          PaymentDto createPayment = paymentService.createPayment(paymentDto);
          return ResponseEntity.ok().body(createPayment);
      } catch (EmptyFields e) {
          return ResponseEntity.badRequest().body(e.getMessage());
      }
    }

    // updatePaymentStatus
    @PatchMapping("/")
    public ResponseEntity<?> updatePaymentStatus(@RequestBody PaymentDto paymentDto) {
        try {
            PaymentDto updatePayment = paymentService.updatePaymentStatus(paymentDto);
            return ResponseEntity.ok().body(updatePayment);
        } catch (PaymentNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    // editPaymentDetails
    @PatchMapping("/")
    public ResponseEntity<?> editPaymentDetails(@RequestBody PaymentDto paymentDto) {
        try {
            PaymentDto editPayment = paymentService.editPaymentDetails(paymentDto);
            return ResponseEntity.ok().body(editPayment);
        } catch (PaymentNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    // deletePayment
    @DeleteMapping("/{billing_id}")
    public ResponseEntity<?> deletePayment(@PathVariable("payment_id") int id) {
        try {
            boolean deleted = paymentService.deletePayment(id);
            return ResponseEntity.ok().body(deleted);
        } catch (PaymentNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }
}
