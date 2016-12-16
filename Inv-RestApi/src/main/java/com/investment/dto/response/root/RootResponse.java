package com.investment.dto.response.root;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class RootResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}



