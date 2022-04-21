package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.BillingDetailsRepository;
import com.revature.spaceecobackend.exception.BillingDetailsNotFound;
import com.revature.spaceecobackend.exception.EmptyFields;
import com.revature.spaceecobackend.model.BillingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BillingDetailsService {
    @Autowired
    BillingDetailsRepository billingDetailsRepository;

    public BillingDetails getBillingDetailById(int id){
        Optional<BillingDetails> optional = billingDetailsRepository.findById(id);
        return optional.orElse(null);
    }


    public BillingDetails updateBillingDetails(BillingDetails billingDetails) throws BillingDetailsNotFound {
        Optional<BillingDetails> optional = billingDetailsRepository.findById(billingDetails.getId());
        if (optional.isPresent()){
            billingDetailsRepository.saveAndFlush(billingDetails);
        }else {
            throw new BillingDetailsNotFound("Payment info with id" + billingDetails.getId() + " was not found");
        }
        return billingDetails;
    }


    public boolean deleteBillingDetails(BillingDetails billingDetails) throws BillingDetailsNotFound {
        Optional<BillingDetails> billingDetailToDelete = billingDetailsRepository.findById(billingDetails.getId());

        if(billingDetailToDelete.isPresent()){
            billingDetailsRepository.delete(billingDetailToDelete.get());
        }else{
            throw new BillingDetailsNotFound("Payment info with id "+ billingDetails.getId()+ " was not found");
        }
        return true;
    }


    public BillingDetails createBillingDetail(BillingDetails billingDetails) throws EmptyFields{
        if(!isBillingDetailsValid(billingDetails)) {
            throw new EmptyFields("All fields are required to save billing details.");
        } else {
            return billingDetailsRepository.saveAndFlush(billingDetails);
        }

    }

    private boolean isBillingDetailsValid(BillingDetails billingDetails){
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
