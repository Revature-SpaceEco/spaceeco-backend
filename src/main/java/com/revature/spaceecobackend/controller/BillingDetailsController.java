package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.BillingDetailsDto;
import com.revature.spaceecobackend.exception.EmptyFields;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.BillingDetails;
import com.revature.spaceecobackend.service.BillingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users/{userId}/billing")
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class BillingDetailsController {
  @Autowired
  private BillingDetailsService billingDetailsService;

  @GetMapping("/{billing_id}")
  public ResponseEntity<?> getBillingById(@PathVariable("billing_id") int id) {
    BillingDetails billingDetails = billingDetailsService.getBillingDetailById(id);

    if (billingDetails != null) {
      return ResponseEntity.ok().body(billingDetails);
    } else {
      return ResponseEntity.status(404).body("Billing details not found.");
    }
  }

  @PatchMapping("/{billing_id}")
  public ResponseEntity<?> updateBillingDetails(@PathVariable("billing_id") int id, @RequestBody BillingDetailsDto billingDetailsDto) {
    try {
      BillingDetails billingDetails;
      billingDetails = billingDetailsService.updateBillingDetails(billingDetailsDto, id);
      return ResponseEntity.ok().body(billingDetails);
    } catch (NotFound e) {
      return ResponseEntity.status(404).body(e.getMessage());
    }
  }

  @PostMapping("/")
  public ResponseEntity<?> addBillingDetails(@RequestBody BillingDetailsDto billingDetailsDto) {
    try {
      BillingDetails billingDetails = billingDetailsService.createBillingDetail(billingDetailsDto);
      return ResponseEntity.ok().body(billingDetails);
    } catch (EmptyFields e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
  }


  @PostMapping("/user")
  public ResponseEntity<?> saveBillingDetails(@RequestBody BillingDetailsDto billingDetailsDto) {
    try {
      BillingDetails billingDetails1 = billingDetailsService.createBillingDetail(billingDetailsDto);
      return ResponseEntity.ok().body(billingDetails1);
    } catch (EmptyFields e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
  }


  @DeleteMapping("/{billing_id}")
  public ResponseEntity<?> deleteBillingDetails(@PathVariable("billing_id") int id) {
    try {
      boolean deleted = billingDetailsService.deleteBillingDetails(id);
      return ResponseEntity.ok().body(deleted);
    } catch (NotFound e) {
      return ResponseEntity.status(404).body(e.getMessage());
    }
  }
}
