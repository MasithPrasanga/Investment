package com.investment.dto.request;

import java.io.Serializable;

public class PasswordResetRequestDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String email;
	
	private String currentPassword;
	
	private String newPassword;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}





