package com.dbs.bank.model;



import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;



@Entity
@Table()
public class Customer implements Serializable{
	
	public long getCust_id() {
		return cust_id;
	}


	public void setCust_id(long cust_id) {
		this.cust_id = cust_id;
	}

	@Column(columnDefinition="boolean default true")
	private boolean activated;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cust_id;
	
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private String phoneNumber;
	
	@Column(unique = true)
	private String panID;
	
	private String gender;
	private LocalDate dateOfBirth;
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Account> accounts = new HashSet<>();


	
	Customer(){}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActivated() {
		return activated;
	}


	public void setActivated(boolean activated) {
		this.activated = activated;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getPanID() {
		return panID;
	}


	public void setPanID(String panID) {
		this.panID = panID;
	}


	public Customer(boolean activated, long cust_id, String password, String firstname, String lastname, String email,
			String phoneNumber, String panID, String gender, LocalDate dateOfBirth) {
		super();
		this.activated = activated;
		this.cust_id = cust_id;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.panID = panID;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}


	@Override
	public String toString() {
		return "Customer [activated=" + activated + ", cust_id=" + cust_id + ", password=" + password + ", firstname="
				+ firstname + ", lastname=" + lastname + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", panID=" + panID + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + "]";
	}


}
