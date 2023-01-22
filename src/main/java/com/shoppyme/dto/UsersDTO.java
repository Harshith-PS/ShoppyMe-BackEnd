package com.shoppyme.dto;

import java.time.LocalDate;

public class UsersDTO {

	private String emailId;
	private String userPassword;
	private RolesDTO role;
	private char gender;
	private LocalDate dateOfBirth;
	private String address;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public RolesDTO getRole() {
		return role;
	}
	public void setRole(RolesDTO role) {
		this.role = role;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
