package com.AllInOne.AllInOne.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Users1204 {
	
	@Id
	private String username;
	private String password;
	private String roles;
	public Users1204() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users1204(String username, String password, String roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "Users1204 [username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}
}
