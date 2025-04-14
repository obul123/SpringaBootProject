package com.AllInOne.AllInOne.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AllInOne.AllInOne.RestClients.RestClientServiceClass;
import com.AllInOne.AllInOne.entities.Account1204;
import com.AllInOne.AllInOne.entities.AccountResponseClass;
import com.AllInOne.AllInOne.entities.Customer1204;
import com.AllInOne.AllInOne.entities.Users1204;
import com.AllInOne.AllInOne.entities.Users12041;
import com.AllInOne.AllInOne.entities.WhehterRoot;
import com.AllInOne.AllInOne.exceptions.CustomerNotFoundException;
import com.AllInOne.AllInOne.jwt.JwtClass;
import com.AllInOne.AllInOne.repositories.Users1204Repo;
import com.AllInOne.AllInOne.securities.UserDetailsServiceClass;
import com.AllInOne.AllInOne.services.MainService1204;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
public class MainController1204 {
	
	@Autowired
	private MainService1204 mainService1204;
	
	@Autowired
	private RestClientServiceClass serviceClass;
	
	@Autowired
	private JwtClass jwtClass;
	
	@Autowired
	private UserDetailsServiceClass serviceClassa;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Tag(name = "Customer Management", description = "manages customer operations" )
	@Operation(summary = "create customer",description = "This api is used to create customer")
	@RequestMapping(method = RequestMethod.POST , value="/customerManagement/createCustomer")
	public ResponseEntity createCustomer(@RequestBody @Valid Customer1204 customer1204)
	{
		Customer1204 customer12042 = mainService1204.createCustomer(customer1204);
		return new ResponseEntity(customer12042, HttpStatus.CREATED);	
	}
	
	@Tag(name = "Account Management", description = "manages account operations" )
	@Operation(summary = "create account",description = "This api is used to create account")
	@RequestMapping(method = RequestMethod.POST , value="/accountManagement/createAccount")
	public ResponseEntity createAccount(@Valid @RequestBody Account1204 account1204)
	{
		Account1204 account12042 = mainService1204.createAccount(account1204);
		if(account12042==null)
		{
			throw new CustomerNotFoundException("Customer not found with aadhar number - "+account1204.getAadharNumber());
		}
		return new ResponseEntity(account12042, HttpStatus.CREATED);	
	}
	
	@Tag(name = "Account Management", description = "manages account operations" )
	@Operation(summary = "account list",description = "This api is used to get customer account")
	@GetMapping("/accountManagement/accountList")
	public ResponseEntity getCustomerAccounts(
			@RequestParam(required = true, name = "aadharNumberr" ) int aadharNumber,
			@RequestParam(required = false, name = "pageNum", defaultValue = "1" ) int pageNumber,
			@RequestParam(required = false, name = "pageSize", defaultValue = "22" ) int pageSize) {
		Page<Account1204> list = mainService1204.getAllCustomerAccoutns(aadharNumber,pageNumber,pageSize);
		if(list.getTotalElements()==0)
		{
			throw new CustomerNotFoundException("Accounts are not available with aadhar number - "+aadharNumber);
		}
		AccountResponseClass responseClass = new AccountResponseClass();
		responseClass.setPageNum(pageNumber);
		responseClass.setPaseSize(pageSize);
		responseClass.setTotalNumberOfPages(list.getTotalPages());
		responseClass.setTotalNumberOfElements((int)list.getTotalElements());
		responseClass.setList(list.getContent());
		responseClass.setHasNext("N");
		if((pageNumber*pageSize)<list.getTotalElements())
			responseClass.setHasNext("Y");
		return new ResponseEntity(responseClass, HttpStatus.OK);
	}
	
	@Tag(name = "Account Management", description = "manages account operations" )
	@Operation(summary = "account details",description = "This api is used to fetch account details")
	@RequestMapping(method = RequestMethod.GET , value="/accountManagement/accountDetails")
	public ResponseEntity fetchAccountDetails(@RequestParam(required = true ) String accountNumber,
			@RequestParam(required = false) Integer branchCode)
	{
		System.out.println("branch code is X"+branchCode+"X");
		Account1204 account12042 = mainService1204.fetchAccountDetails(accountNumber,branchCode);
		if(account12042==null)
		{
			throw new CustomerNotFoundException("account not found with account number - "+accountNumber);
		}
		return new ResponseEntity(account12042, HttpStatus.CREATED);	
	}
	
	@Tag(name = "Whether Management",description = "provides whehter information")
	@Operation(summary = "wether information",description = "This api is used to provide whather information")
	@GetMapping("/wetherManagement/fetchWhetherInformation")
	public ResponseEntity getLocation(@RequestParam(required = true , value = "lonngitude") double latitude ,
			@RequestParam(required = true) double longitude)
	{
		WhehterRoot root= serviceClass.getWhetherDetails(latitude, longitude);
		
		return new ResponseEntity(root, HttpStatus.OK);
				
	}
	
	@Tag(name = "User Management",description = "User Management")
	@Operation(summary = "User information",description = "This api is used to create user")
	@PostMapping("/userManagement/createUserInformation")
	public ResponseEntity createUser(@RequestBody Users1204 users1204)
	{
		mainService1204.createUser(users1204);
		
		return new ResponseEntity(users1204, HttpStatus.OK);
				
	}
	
	@Tag(name = "JWT Management",description = "JWT Management")
	@Operation(summary = "Jwt information",description = "This api is used to create Jwt Token")
	@PostMapping("/JwtManagement/createJwtToken")
	public String createJwtToken(@RequestBody Users12041 users1204)
	{
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(users1204.getUsername(), users1204.getPassword()));
		
		if(!authentication.isAuthenticated())
			throw new UsernameNotFoundException("Username not found");
		UserDetails service= serviceClassa.loadUserByUsername(users1204.getUsername());		
		return jwtClass.generateJwtToken(users1204);
		
	}
	
}
