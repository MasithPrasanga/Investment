package com.investment.dto.response;

import java.io.Serializable;
import java.util.Date;

public class RawProjectInfoResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;

	private String projectName;

	private Date submitedDate;
    
	private CoreUserResponseDto entrepreneur = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getSubmitedDate() {
		return submitedDate;
	}

	public void setSubmitedDate(Date submitedDate) {
		this.submitedDate = submitedDate;
	}

	public CoreUserResponseDto getEntrepreneur() {
		return entrepreneur;
	}

	public void setEntrepreneur(CoreUserResponseDto entrepreneur) {
		this.entrepreneur = entrepreneur;
	}
	
}












































