package com.revature.spaceecobackend.dao;

import com.revature.spaceecobackend.model.BillingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingDetailsRepository extends JpaRepository<BillingDetails, Integer> {
}
