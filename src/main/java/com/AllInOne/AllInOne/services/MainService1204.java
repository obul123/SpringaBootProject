package com.AllInOne.AllInOne.services;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.AllInOne.AllInOne.entities.Account1204;
import com.AllInOne.AllInOne.entities.Customer1204;
import com.AllInOne.AllInOne.entities.Users1204;
import com.AllInOne.AllInOne.repositories.Account1204Repo;
import com.AllInOne.AllInOne.repositories.Customer1204Repo;
import com.AllInOne.AllInOne.repositories.Users1204Repo;

import jakarta.validation.Valid;

@Service
public class MainService1204 {
	
	@Autowired
	private Account1204Repo account1204Repo;
	
	@Autowired
	private Customer1204Repo customer1204Repo;
	
	@Autowired
	private Users1204Repo users1204Repo;

	public Customer1204 createCustomer(@Valid Customer1204 customer1204) {
		return customer1204Repo.save(customer1204);
	}

	public Account1204 createAccount(@Valid Account1204 account1204) {
		Optional<Customer1204> customer = customer1204Repo.findById((long)account1204.getAadharNumber());
		if(!customer.isPresent())
			return null;
		return account1204Repo.save(account1204);
	}

	public Page<Account1204> getAllCustomerAccoutns(int aadharNumber, int pageNumber, int pageSize) {

		return account1204Repo.getAllCusotmerAcocunts(aadharNumber, PageRequest.of(pageNumber-1, pageSize));
	}

	public Account1204 fetchAccountDetails(String accountNumber, Integer branchCode) {
		return account1204Repo.getAccountDetails(accountNumber, branchCode);
	}
	
	public void createUser(Users1204 users1204) {
		users1204Repo.save(users1204);
	}

	

}
