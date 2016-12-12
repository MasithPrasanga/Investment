package com.investment.dto.response;

import java.io.Serializable;
import java.util.List;

import com.investment.entity.CoreUser;

public class EntrepreneurProjectsDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private CoreUser coreUser = null;
	
	private List<RawProjectInfoResponseDto> rawProjectList = null;
	
	private List<ProssedProjectInfoResponseDto> processedProjectList = null;

	public CoreUser getCoreUser() {
		return coreUser;
	}

	public void setCoreUser(CoreUser coreUser) {
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

	@Override
	public String toString() {
		return "EntrepreneurProjectsDto [coreUser=" + coreUser + ", rawProjectList=" + rawProjectList
				+ ", processedProjectList=" + processedProjectList + "]";
	}
	
	

}


































