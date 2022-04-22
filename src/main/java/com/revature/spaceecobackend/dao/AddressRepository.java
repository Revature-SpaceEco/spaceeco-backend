package com.revature.spaceecobackend.dao;

import com.revature.spaceecobackend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("SELECT address FROM Address address WHERE address.id =?1")
    Optional<Address> findAddressByUserId(int id);

}
