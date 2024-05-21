package com.peytosoft.AuthService.Model;




public class Doctor {
	
	private Integer id;
	
	private String firstName;
	private String lastName;
	private String department;
	private String speciality;
	private String contactNo;
	private String email;
	private String designation;
	
	public Doctor() {}
	
	
	
	public Doctor(Integer id, String email) {
		super();
		this.id = id;
		this.email = email;
	}



	public Doctor(Integer id, String firstName, String lastName, String department, String speciality,
			String contactNo, String email, String designation) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.speciality = speciality;
		this.contactNo = contactNo;
		this.email = email;
		this.designation = designation;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	
	

}
