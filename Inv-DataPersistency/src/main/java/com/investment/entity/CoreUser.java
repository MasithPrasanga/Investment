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
	
	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "email")
	private String userEmail;

	@Column(name = "password")
	private String password;
	
	@Column(name = "mobilenumber")
	private String mobileNumber;

	@Column(name = "landnumber")
	private String landNumber;

	@Column(name = "birthdate")
	private Date birthDate;
	
	@Column(name = "gender")
	private String gender;

	@Column(name = "account_type")
	private String accountType;

	@Column(name = "activation_code")
	private String activationCode;
	
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "activated_date")
	private Date activatedDate;

	@Column(name = "activation_status")
	private String activationStatus;

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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
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

	public String getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(String activationStatus) {
		this.activationStatus = activationStatus;
	}

	@Override
	public String toString() {
		return "CoreUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userEmail=" + userEmail
				+ ", password=" + password + ", mobileNumber=" + mobileNumber + ", landNumber=" + landNumber
				+ ", birthDate=" + birthDate + ", gender=" + gender + ", accountType=" + accountType
				+ ", activationCode=" + activationCode + ", createdDate=" + createdDate + ", activatedDate="
				+ activatedDate + ", activationStatus=" + activationStatus + "]";
	}	

}










































