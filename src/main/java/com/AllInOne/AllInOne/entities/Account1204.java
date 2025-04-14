package com.AllInOne.AllInOne.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Schema(name = "accountDeatils",description = "account details")
public class Account1204 {
	
	@Schema(description = "aadharNumber")
	private int aadharNumber;
	@Id
	@Schema(description = "accountNumber")
	private String accountNumber;
	@Schema(description = "accountType - savings / current")
	private String accountType;
	@Schema(description = "brahc number")
	private long branchCode;
	@Schema(description = "brach name")
	private String branchName;
	public Account1204() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account1204(int aadharNumber, String accountNumber, String accountType, long branchCode, String branchName) {
		super();
		this.aadharNumber = aadharNumber;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.branchCode = branchCode;
		this.branchName = branchName;
	}
	public int getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(int aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public long getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(long branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	@Override
	public String toString() {
		return "Account1204 [aadharNumber=" + aadharNumber + ", accountNumber=" + accountNumber + ", accountType="
				+ accountType + ", branchCode=" + branchCode + ", branchName=" + branchName + "]";
	}
	
	

}
