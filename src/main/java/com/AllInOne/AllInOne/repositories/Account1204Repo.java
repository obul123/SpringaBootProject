package com.AllInOne.AllInOne.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AllInOne.AllInOne.entities.Account1204;

@Repository
public interface Account1204Repo extends JpaRepository<Account1204, String>{

	@Query(value = "select * from Account1204 where aadhar_Number = ?1 " , nativeQuery = true )
	public Page<Account1204> getAllCusotmerAcocunts(int aadharNumber,Pageable pageable);
	
	@Query(value = "select * from account1204 where ACCOUNT_NUMBER = ?1 and (:branchCode is null or BRANCH_CODE = :branchCode)", nativeQuery =true)
	public Account1204 getAccountDetails(String accountNumber,Integer branchCode);
}
