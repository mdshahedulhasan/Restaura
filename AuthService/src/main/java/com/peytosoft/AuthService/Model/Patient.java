package com.peytosoft.AuthService.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;


public class Patient {
	
	private Integer id;
	
	private String firstName;
	private String lastName;
	private String address;
	private Integer contactNo1;
	private Integer contactNo2;
	private String email;
	

	public Patient() {}
	
	public Patient(Integer id, String email) {
		super();
		this.id = id;
		this.email = email;
	}
	
	
	public Patient(Integer id, String firstName, String lastName, String address, Integer contactNo1,
			Integer contactNo2, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contactNo1 = contactNo1;
		this.contactNo2 = contactNo2;
		this.email = email;

	}




	public Integer getId() {
		return id;
	}
	
	public void setPatientId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getContactNo1() {
		return contactNo1;
	}
	public void setContactNo1(Integer contactNo1) {
		this.contactNo1 = contactNo1;
	}
	public Integer getContactNo2() {
		return contactNo2;
	}
	public void setContactNo2(Integer contactNo2) {
		this.contactNo2 = contactNo2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
