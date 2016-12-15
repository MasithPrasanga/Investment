package com.investment.dto.response;

import java.io.Serializable;
import java.util.List;

import com.investment.entity.CoreUser;

public class EntrepreneurProjectsDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private CoreUserResponseDto coreUser = null;
	
	private List<RawProjectInfoResponseDto> rawProjectList = null;
	
	private List<ProssedProjectInfoResponseDto> processedProjectList = null;
	
	public CoreUserResponseDto getCoreUser() {
		return coreUser;
	}

	public void setCoreUser(CoreUserResponseDto coreUser) {
		this.coreUser = coreUser;
	}

	public List<RawProjectInfoResponseDto> getRawProjectList() {
		return rawProjectList;
	}

	public void setRawProjectList(List<RawProjectInfoResponseDto> rawProjectList) {
		this.rawProjectList = rawProjectList;
	}

	public List<ProssedProjectInfoResponseDto> getProcessedProjectList() {
		return processedProjectList;
	}

	public void setProcessedProjectList(List<ProssedProjectInfoResponseDto> processedProjectList) {
		this.processedProjectList = processedProjectList;
	}
}


































