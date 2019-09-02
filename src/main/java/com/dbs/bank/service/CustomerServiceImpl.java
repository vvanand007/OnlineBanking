package com.dbs.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dbs.bank.model.Customer;
import com.dbs.bank.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService{
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    
    @Override
    @Transactional
    public List<Customer> listAll(){
    	return customerRepository.findAll();
    }
    
    @Override
    @Transactional
    public Customer saveCustomer(Customer customer) {
    	if((customerRepository.findByPanID(customer.getPanID())==null)
    			&& (customerRepository.findByEmail(customer.getEmail())==null)
    					&& (customerRepository.findByPhoneNumber(customer.getPhoneNumber())==null))
    	{
    		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    		customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
    		return customerRepository.save(customer);
    	}
    	return null;
    }
    
    @Override
    @Transactional
    public Customer findById(long id) {
    	return this.customerRepository.findById(id).get();
    }
    
    @Override
    @Transactional
    public Customer updateCustomer(Customer customerDetails) {
    	Customer customer = customerRepository.findById(customerDetails.getCust_id()).get();
    	customer.setFirstname(customerDetails.getFirstname());
    	customer.setActivated(customerDetails.isActivated());
    	customer.setLastname(customerDetails.getLastname());
    	customer.setEmail(customerDetails.getEmail());
    	customer.setPhoneNumber(customerDetails.getPhoneNumber());
    	customer.setPanID(customerDetails.getPanID());
    	customer.setGender(customerDetails.getGender());
    	customer.setDateOfBirth(customerDetails.getDateOfBirth());
    	customer.setActivated(true);
    	return customerRepository.save(customer);
    	
    }
     
    @Override
    @Transactional
    public ResponseEntity<?> deleteCustomer(long id){
    	Customer customer = customerRepository.findById(id).get();
    	customerRepository.delete(customer);
    	return ResponseEntity.ok().build();
    }
    
    
    
    @Override
    @Transactional
	public Optional<Customer> findByEmailAndPassword(String email, String password) {
    	Customer currentCustomer = findByEmail(email);
    	try{
    		if(bCryptPasswordEncoder.matches(password, currentCustomer.getPassword())) {
    		return this.customerRepository.findByEmailAndPassword(email, currentCustomer.getPassword());
    		}
    	}
    	catch(Exception e) {
    		return null;
    	}

		return null;
	}

	@Override
	public Customer findByPanID(String panID) {
		// TODO Auto-generated method stub
		return this.customerRepository.findByPanID(panID);
	}

	@Override
	public Customer findByEmail(String email) {
		// TODO Auto-generated method stub
		return this.customerRepository.findByEmail(email);
	}

	@Override
	public Customer findByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		return this.customerRepository.findByPhoneNumber(phoneNumber);
	}
}
