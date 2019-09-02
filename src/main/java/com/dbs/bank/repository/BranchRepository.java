package com.dbs.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.bank.model.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>{

	Branch findByBranchName(String branchName);

}