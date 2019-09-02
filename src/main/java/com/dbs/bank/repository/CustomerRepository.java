package com.dbs.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.bank.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Optional<Customer> findByEmailAndPassword(String email, String password);

	Customer findByPanID(String panID);
	
	Customer findByEmail(String email);
	
	Customer findByPhoneNumber(String phoneNumber);
}
