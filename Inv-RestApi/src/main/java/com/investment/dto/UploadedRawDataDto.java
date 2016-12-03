package com.investment.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UploadedRawDataDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<String> urls = new ArrayList<String>();

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	
}






