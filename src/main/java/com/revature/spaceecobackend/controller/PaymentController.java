package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.PaymentDto;
import com.revature.spaceecobackend.exception.EmptyFields;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.Payment;
import com.revature.spaceecobackend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users/{userId}/payments")
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class PaymentController {

  @Autowired
  PaymentService paymentService;

  // getPaymentById
  @GetMapping("/{payment_id}")
  public ResponseEntity<?> getPaymentById(@PathVariable("payment_id") int id) {

    Payment payment = null;
    try {
      payment = paymentService.getPaymentById(id);
      return ResponseEntity.ok().body(payment);
    } catch (NotFound e) {
      return ResponseEntity.notFound().build();
    }
  }

  // createPayment
  @PostMapping()
  public ResponseEntity<?> createPayment(@RequestBody PaymentDto paymentDto) {
    try {
      Payment createPayment = paymentService.createPayment(paymentDto);
      return ResponseEntity.ok().body(createPayment);
    } catch (EmptyFields e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PatchMapping()
  public ResponseEntity<?> editPaymentDetails(@RequestBody PaymentDto paymentDto) {
    try {
      Payment editPayment = paymentService.updatePayment(paymentDto);
      return ResponseEntity.ok().body(editPayment);
    } catch (NotFound e) {
      return ResponseEntity.notFound().build();
    }
  }

  // deletePayment
  @DeleteMapping("/{payment_id}")
  public ResponseEntity<?> deletePayment(@PathVariable("payment_id") int id) {
    try {
      boolean deleted = paymentService.deletePaymentById(id);
      return ResponseEntity.ok().body(deleted);
    } catch (NotFound e) {
      return ResponseEntity.notFound().build();
    }
  }
}