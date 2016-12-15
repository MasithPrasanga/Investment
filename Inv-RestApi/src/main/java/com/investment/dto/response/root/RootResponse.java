package com.investment.dto.response.root;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class RootResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}



