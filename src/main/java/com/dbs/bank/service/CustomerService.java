package com.dbs.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.dbs.bank.model.Customer;

public interface CustomerService {

	List<Customer> listAll();

	Customer saveCustomer(Customer customer);

	Customer findById(long id);

	Customer updateCustomer(Customer customerDetails);

	ResponseEntity<?> deleteCustomer(long id);
	
	Optional<Customer> findByEmailAndPassword(String email,String password);
	
	Customer findByPanID(String panID);
	
	public Customer findByEmail(String email);

	public Customer findByPhoneNumber(String phoneNumber);

}
