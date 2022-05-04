package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.PaymentRepository;
import com.revature.spaceecobackend.dto.AddressDTO;
import com.revature.spaceecobackend.dto.PaymentDto;
import com.revature.spaceecobackend.exception.EmptyFields;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.Address;
import com.revature.spaceecobackend.model.BillingDetails;
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
    BillingDetailsService billingDetailsService;

    @Autowired
    AddressService addressService;

    @Autowired
    private ModelMapper modelMapper;

    public Payment getPaymentById(int id) throws NotFound {
        Optional<Payment> optional = paymentRepository.findById(id);
        if (optional.isPresent()) return optional.get();
        throw new NotFound("Payment with id: " + id + "not found");
    }

    public Payment createPayment(PaymentDto paymentDto) throws EmptyFields {
        paymentDto.setStatus("Pending");
        if (paymentDto.getBillingDetails() == null) {
            throw new EmptyFields("Payment missing information");
        }
        Address address = addressService.createAddressOrder(modelMapper.map(paymentDto.getBillingDetails().getAddress(), AddressDTO.class));
        paymentDto.getBillingDetails().setAddress(address);
        BillingDetails billingDetails = billingDetailsService.createBillingDetail(paymentDto.getBillingDetails());
        Payment payment = modelMapper.map(paymentDto, Payment.class);
        payment.setBillingDetails(billingDetails);
        return paymentRepository.saveAndFlush(payment);
    }

    public Payment updatePayment(PaymentDto paymentDto) throws NotFound {
        Payment payment = modelMapper.map(paymentDto, Payment.class);

        Optional<Payment> optional = paymentRepository.findById(payment.getId());

        if (optional.isPresent()) {
            paymentRepository.saveAndFlush(payment);
            return optional.get();
        } else {
            throw new NotFound("Payment info with id" + payment.getId() + " was not found");
        }

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