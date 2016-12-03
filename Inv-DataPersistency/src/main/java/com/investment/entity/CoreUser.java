package com.investment.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "core_user")
public class CoreUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "firstname", unique = true, nullable = false)
	private String firstName;

	@Column(name = "lastname", unique = true, nullable = false)
	private String lastName;
	
	@Column(name = "email", unique = true, nullable = false)
	private String userEmail;

	@Column(name = "password", unique = true, nullable = false)
	private String password;
	
	@Column(name = "mobilenumber", unique = true, nullable = false)
	private String mobileNumber;

	@Column(name = "landnumber", unique = true, nullable = false)
	private String landNumber;

	@Column(name = "birthdate", unique = true, nullable = false)
	private Date birthDate;
	
	@Column(name = "gender", unique = true, nullable = false)
	private String gender;

	@Column(name = "account_type", unique = true, nullable = false)
	private int accountType;

	@Column(name = "activation_code", unique = true, nullable = false)
	private String activationCode;
	
	@Column(name = "created_date", unique = true, nullable = false)
	private Date createdDate;

	@Column(name = "activated_date", unique = true, nullable = false)
	private Date activatedDate;

	@Column(name = "activation_status", unique = true, nullable = false)
	private int activationStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getLandNumber() {
		return landNumber;
	}

	public void setLandNumber(String landNumber) {
		this.landNumber = landNumber;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getActivatedDate() {
		return activatedDate;
	}

	public void setActivatedDate(Date activatedDate) {
		this.activatedDate = activatedDate;
	}

	public int getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(int activationStatus) {
		this.activationStatus = activationStatus;
	}

}






































