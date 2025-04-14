package com.AllInOne.AllInOne.entities;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Schema(name = "Customer Details" , description = "Customer full details")
public class Customer1204 {
	
	@Id
	@Schema(description = "addhar number")
	private long aadharNumber;
	@NotNull
	@Schema(description = "customer name")
	private String customerName;
	@Schema(description = "mobile number")
	private BigDecimal mobileNumber;
	@NotBlank
	@Schema(description = "customer address")
	private String customerAddress;
	@Schema(description = "marital sttaus - single / married")
	private String maritalStatus;
	@Schema(description = "gender - male / female / LGBTQ")
	private String gender;
	public Customer1204() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Customer1204(long aadharNumber, @NotNull String customerName, BigDecimal mobileNumber,
			@NotBlank String customerAddress, String maritalStatus, String gender) {
		super();
		this.aadharNumber = aadharNumber;
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.customerAddress = customerAddress;
		this.maritalStatus = maritalStatus;
		this.gender = gender;
	}



	public long getAadharNumber() {
		return aadharNumber;
	}



	public void setAadharNumber(long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}



	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public BigDecimal getMobileNumber() {
		return mobileNumber;
	}



	public void setMobileNumber(BigDecimal mobileNumber) {
		this.mobileNumber = mobileNumber;
	}



	public String getCustomerAddress() {
		return customerAddress;
	}



	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}



	public String getMaritalStatus() {
		return maritalStatus;
	}



	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	
}
