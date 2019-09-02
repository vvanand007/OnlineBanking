package com.dbs.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.bank.model.Account;
import com.dbs.bank.model.Customer;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	List<Account> findByCustomer(Customer id);
	Account findByAccountType(String accountType);
}