package com.dbs.bank.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dbs.bank.model.Account;
import com.dbs.bank.model.Transaction;
import com.dbs.bank.repository.AccountRepository;
import com.dbs.bank.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private EmailService emailService;
	
	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		super();
		this.transactionRepository=transactionRepository;
	}
	
	@Override
	@Transactional
	public List<Transaction> listAll() {
		return this.transactionRepository.findAll();
	}

	@Override
	@Transactional
	public Transaction saveTransaction(Transaction transaction) {
			
			List<Transaction> transactions=transactionRepository.findByFromAccountAndDate(transaction.getFromAccount(),Date.valueOf(LocalDate.now()));
			long sum=0;
			for(int i=0;i<transactions.size();i++) {
				if(transactions.get(i).getDate()==(new java.util.Date()));
						System.out.println("yooo");
						sum=sum+transactions.get(i).getAmmount();
			}
				System.out.println("\n"+transactions+"\n"+sum);
				double fromAccountBalance = transaction.getFromAccount().getBalance();
		        double toAccountBalance = transaction.getToAccount().getBalance();
		        if((fromAccountBalance - transaction.getAmmount()) < 5000) {
		            return null;
		        }
		        else {
		        	transaction.setTime(java.time.LocalTime.now());
			        transaction.setDate(Date.valueOf(LocalDate.now())); 
		            fromAccountBalance = fromAccountBalance - transaction.getAmmount();
		            transaction.getFromAccount().setBalance(fromAccountBalance);
		            transaction.setTime(java.time.LocalTime.now());
					this.accountRepository.save(transaction.getFromAccount());
		            if(sum+transaction.getAmmount()<=10000) {
		            	toAccountBalance = toAccountBalance + transaction.getAmmount();
		            	transaction.getToAccount().setBalance(toAccountBalance);
		            	System.out.println(transaction.getToAccount().getBalance());
						this.accountRepository.save(transaction.getToAccount());
//		            	this.emailService.sendMail(transaction.getFromAccount().getCustomer().getEmail()
//		            			,"Transaction Successfull", "Your Transaction of "+transaction.getAmmount()+" is success and has been transferred to the reciever \nThanks for using our Banking Services");
		            }
		            else {
		            	transaction.setFlag(true);
//				        this.emailService.sendMail(transaction.getFromAccount().getCustomer().getEmail()
//		            			,"Transaction Pedning", "Your Transaction of "+transaction.getAmmount()+" is under pending, Please await for the bank approval \nThanks for using our Banking Services");
		            }
	            	return transactionRepository.save(transaction);

		        }

	}

	@Override
	@Transactional
	public Optional<List<Transaction>> findByFromAccount(Account id) {
		return this.transactionRepository.findByFromAccount(id);
	}

	@Override
	@Transactional
	public Transaction findById(long id) {
		return this.transactionRepository.findById(id).get();
	}

	@Override
	@Transactional
	public Optional<List<Transaction>> findByToAccount(Account id) {
		return this.transactionRepository.findByToAccount(id);
		
	}

	@Override
	public Optional<List<Transaction>> findByFromAccountOrToAccount(Account id) {
		return this.transactionRepository.findByFromAccountOrToAccount(id,id);
	}
	

	@Override
    @Transactional
    public Transaction updateTransaction(Transaction transaction) {
        if(transaction.isFlag()) {
	        double toAccountBalance = transaction.getToAccount().getBalance();       
	        toAccountBalance = toAccountBalance + transaction.getAmmount();
	        transaction.getToAccount().setBalance(toAccountBalance);
	        transaction.setFlag(false);
			this.accountRepository.save(transaction.getToAccount());
			this.emailService.sendMail(transaction.getFromAccount().getCustomer().getEmail()
        			,"Transaction Successfull", "Your Transaction of "+transaction.getAmmount()+" is success/ approved by the bank and has been transferred to the reciever \nThanks for using our Banking Services");
	        return this.transactionRepository.save(transaction);
        }
        return null;
    }
	
	@Override
    @Transactional
    public Transaction rejectTransaction(Transaction transaction) {
       
	        double fromAccountBalance = transaction.getFromAccount().getBalance();       
	        fromAccountBalance = fromAccountBalance + transaction.getAmmount();
	        transaction.getFromAccount().setBalance(fromAccountBalance);
	        transaction.setAmmount(0);
	        transaction.setFlag(false);
			this.accountRepository.save(transaction.getFromAccount());
			this.emailService.sendMail(transaction.getFromAccount().getCustomer().getEmail()
        			,"Transaction Rejected", "Your Transaction of "+transaction.getAmmount()+" has been rejected by the bacnk. Please contact bank for further information \nThanks for using our Banking Services");
	        return this.transactionRepository.save(transaction);

    }

}