package com.dbs.bank.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dbs.bank.model.Account;
import com.dbs.bank.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	@Query(value="select * from transaction order by tid desc",nativeQuery=true)
	List<Transaction> findAll();
	
	Optional<List<Transaction>> findByFromAccount(Account id);
	
	Optional<List<Transaction>> findByToAccount(Account id);
	
	Optional<List<Transaction>> findByFromAccountOrToAccount(Account id,Account id1);
	

	List<Transaction> findByFromAccountAndDate(Account fromAccount, Date valueOf);

}