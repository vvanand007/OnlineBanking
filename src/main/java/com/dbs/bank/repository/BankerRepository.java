package com.dbs.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.bank.model.Banker;

@Repository
public interface BankerRepository extends JpaRepository<Banker, Long>{
	Optional<Banker> findByAdminNameAndPassword(String name,String password);
    List<Banker> findAll();
}
