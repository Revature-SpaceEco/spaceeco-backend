package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.BillingDetailsRepository;
import com.revature.spaceecobackend.model.BillingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillingDetailsService {
    @Autowired
    BillingDetailsRepository billingDetailsRepository;
    // TODO. Get Billing By Id
    public BillingDetails getBillingDetailById(int id){
//        Optional<BillingDetails> optional = billingDetailsRepository.findByBillingId(id);
//        if ()
        return null;
    }

    // TODO. Update Billing Details
    public BillingDetails updateBillingDetails(BillingDetails billingDetails) {

        return null;
    }

    // TODO. Delete Billing Details
    public boolean deleteBillingDetails(BillingDetails billingDetails){

        return false;
    }

    // TODO. Create Billing Details
    public boolean createBillingDetail(BillingDetails billingDetails) {
        return false;
    }
}
