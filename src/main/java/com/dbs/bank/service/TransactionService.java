package com.dbs.bank.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.dbs.bank.model.Account;
import com.dbs.bank.model.Transaction;

public interface TransactionService {
	
	List<Transaction> listAll();

	Transaction saveTransaction(Transaction transaction);

	Transaction findById(long id);

	Optional<List<Transaction>> findByFromAccount(Account id);
	
	Optional<List<Transaction>> findByToAccount(Account id);

	Optional<List<Transaction>> findByFromAccountOrToAccount(Account id);
	
	Transaction updateTransaction(Transaction transaction);
	
	Transaction rejectTransaction(Transaction transaction);

}