package com.investment.dto.response;

import java.io.Serializable;

import com.investment.dto.response.root.RootResponse;

public class SignInResponseDto extends RootResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	private String userEmail;
	
	private String accountType;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
}








