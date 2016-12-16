package com.investment.dto.response;

import java.io.Serializable;
import java.util.List;

import com.investment.dto.response.root.RootResponse;

public class EntrepreneurProposalsResponseDto extends RootResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private CoreUserResponseDto entrepreneur = null;
	
	private List<RawProjectInfoResponseDto> rawProjectList = null;
	
	private List<ProssedProjectInfoResponseDto> processedProjectList = null;
	
	public CoreUserResponseDto getEntrepreneur() {
		return entrepreneur;
	}

	public void setEntrepreneur(CoreUserResponseDto entrepreneur) {
		this.entrepreneur = entrepreneur;
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


































