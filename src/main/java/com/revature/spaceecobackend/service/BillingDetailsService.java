package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.BillingDetailsRepository;
import com.revature.spaceecobackend.dto.BillingDetailsDto;
import com.revature.spaceecobackend.exception.EmptyFields;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.BillingDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BillingDetailsService {
    @Autowired
    BillingDetailsRepository billingDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BillingDetails getBillingDetailById(int id){
        Optional<BillingDetails> optional = billingDetailsRepository.findById(id);
        return optional.orElse(null);
    }


    public BillingDetails updateBillingDetails(BillingDetailsDto billingDetailsDto, int id) throws NotFound {
        BillingDetails billingDetails = modelMapper.map(billingDetailsDto, BillingDetails.class);
        billingDetails.setId(id);
        Optional<BillingDetails> optional = billingDetailsRepository.findById(billingDetails.getId());
        if (optional.isPresent()){
            billingDetailsRepository.saveAndFlush(billingDetails);
        }else {
            throw new NotFound("Payment info with id" + billingDetails.getId() + " was not found");
        }
        return billingDetails;
    }


    public boolean deleteBillingDetails(int id) throws NotFound{

        Optional<BillingDetails> billingDetailToDelete = billingDetailsRepository.findById(id);

        if(!billingDetailToDelete.isPresent()){
            throw new NotFound("Payment info with id "+ id + " was not found");
        }
        billingDetailsRepository.delete(billingDetailToDelete.get());
        return true;
    }


    public BillingDetails createBillingDetail(BillingDetailsDto billingDetailsDto) throws EmptyFields{
        if(!isBillingDetailsValid(billingDetailsDto)) {
            throw new EmptyFields("All fields are required to save billing details.");
        } else {
            BillingDetails billingDetails = modelMapper.map(billingDetailsDto, BillingDetails.class);
            return billingDetailsRepository.saveAndFlush(billingDetails);
        }

    }

    private boolean isBillingDetailsValid(BillingDetailsDto billingDetailsDto){
        BillingDetails billingDetails = modelMapper.map(billingDetailsDto, BillingDetails.class);
        if(billingDetails.getBillingName() == null) {
            return false;
        }

        if(billingDetails.getBillingCardNumber() == 0) {
            return false;
        }

        if(billingDetails.getBillingAddress() == null) {
            return false;
        }

        if(billingDetails.getBillingSecurityNumber() == 0) {
            return false;
        }

        if(billingDetails.getBillingCardType() == null) {
            return false;
        }
        return true;
    }
}
