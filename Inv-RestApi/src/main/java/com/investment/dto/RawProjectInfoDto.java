package com.investment.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RawProjectInfoDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int userId;
	
	private List<String> urls = new ArrayList<String>();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	
}

















