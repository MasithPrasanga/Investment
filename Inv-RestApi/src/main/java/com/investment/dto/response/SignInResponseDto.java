package com.investment.dto.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class SignInResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String userEmail;
	
	private HttpStatus status;
	
	private String accountType;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus accepted) {
		this.status = accepted;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
}











