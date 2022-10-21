package com.example.userService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Users")
public class User {
	@Id
	private String email;
	private String name, gender, DOB, address, type, password;
	private long contact;
	private boolean isActive;
	
	public User(String name, String email, String gender, String DOB, String address, String type, String password,
			long contact, boolean isActive) {
		super();
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.DOB = DOB;
		this.address = address;
		this.type = type;
		this.password = password;
		this.contact = contact;
		this.isActive = isActive;
	}
	
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", gender=" + gender + ", DOB=" + DOB + ", address="
				+ address + ", type=" + type + ", password=" + password + ", contact=" + contact + ", isActive="
				+ isActive + "]";
	}
	
}
