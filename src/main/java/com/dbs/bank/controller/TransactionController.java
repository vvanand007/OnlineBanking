package com.dbs.bank.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dbs.bank.model.Account;
import com.dbs.bank.model.Transaction;
import com.dbs.bank.service.TransactionService;
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	
	@GetMapping("/transaction")
	public List<Transaction> getAllTransactions(){
		return transactionService.listAll();
	}
	
	@GetMapping("transaction/{id}")
	public Transaction getTransactionById(@PathVariable("id") Long id) {
		return transactionService.findById(id);
	}
	
	@PostMapping("/transaction")
	public Transaction createTransaction(@RequestBody Transaction transaction) {
		return transactionService.saveTransaction(transaction);
		
	}
	
	@GetMapping("transaction/account/{id}")
	public Optional<List<Transaction>> getTransactionOfToAccountNum(@PathVariable("id") Account id) {
		return transactionService.findByFromAccountOrToAccount(id);
	}
	
	@GetMapping("fromAccount/transaction/{id}")
	public Optional<List<Transaction>> getTransactionOfFromAccountNum(@PathVariable("id") Account id) {
		return transactionService.findByToAccount(id);
	}
	

	@PutMapping("/transaction/cleared")
    public Transaction updateTransaction(@RequestBody Transaction transaction) {
        return transactionService.updateTransaction(transaction);
    }
	
	@PutMapping("/transaction/rejected")
    public Transaction rejectTransaction(@RequestBody Transaction transaction) {
        return transactionService.rejectTransaction(transaction);
    }


}