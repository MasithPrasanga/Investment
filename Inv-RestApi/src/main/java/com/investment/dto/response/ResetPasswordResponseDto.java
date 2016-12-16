package com.investment.dto.response;

import java.io.Serializable;

import com.investment.dto.response.root.RootResponse;

public class ResetPasswordResponseDto extends RootResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}



