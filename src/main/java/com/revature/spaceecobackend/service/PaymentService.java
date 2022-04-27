package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.PaymentRepository;
import com.revature.spaceecobackend.dto.PaymentDto;
import com.revature.spaceecobackend.exception.EmptyFields;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.Payment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PaymentService {
  @Autowired
  PaymentRepository paymentRepository;

  @Autowired
  private ModelMapper modelMapper;

  public Payment getPaymentById(int id) throws NotFound {
    Optional<Payment> optional = paymentRepository.findById(id);
    if (optional.isPresent()) return optional.get();
    throw new NotFound("Payment with id: " + id + "not found");
  }

  public Payment createPayment(PaymentDto paymentDto) throws EmptyFields {
    if (paymentDto.getBillingDetailsDto() == null || !paymentDto.getStatus().equals("Pending")) {
      throw new EmptyFields("Payment missing information");
    }
    Payment payment = modelMapper.map(paymentDto, Payment.class);
    return paymentRepository.saveAndFlush(payment);
  }

  public Payment updatePayment(PaymentDto paymentDto) throws NotFound {
    Payment payment = modelMapper.map(paymentDto, Payment.class);

    Optional<Payment> optional = paymentRepository.findById(payment.getId());

    if (optional.isPresent()) {
      paymentRepository.saveAndFlush(payment);
    } else {
      throw new NotFound("Payment info with id" + payment.getId() + " was not found");
    }
    return payment;
  }

  public boolean deletePaymentById(int id) throws NotFound {

    Optional<Payment> payment = paymentRepository.findById(id);
    if (payment.isPresent()) {
      paymentRepository.delete(payment.get());
    } else {
      throw new NotFound("Order with id " + id + " does not exist");
    }
    return true;
  }
}