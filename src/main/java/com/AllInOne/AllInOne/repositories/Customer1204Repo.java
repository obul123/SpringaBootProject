package com.AllInOne.AllInOne.repositories;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AllInOne.AllInOne.entities.Account1204;
import com.AllInOne.AllInOne.entities.Customer1204;

@Repository
public interface Customer1204Repo extends JpaRepository<Customer1204, Long>{
	
	
}
