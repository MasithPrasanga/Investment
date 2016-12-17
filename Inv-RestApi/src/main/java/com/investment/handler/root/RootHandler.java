package com.investment.handler.root;

import com.investment.dto.response.CoreUserResponseDto;
import com.investment.dto.response.ProssedProjectInfoResponseDto;
import com.investment.entity.CoreUser;
import com.investment.entity.ProcessedProjectInfo;

public class RootHandler {

	public CoreUserResponseDto createCoreUserResponse(CoreUser user){
		CoreUserResponseDto dto = new CoreUserResponseDto();
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setUserEmail(user.getUserEmail());
		dto.setMobileNumber(user.getMobileNumber());
		dto.setLandNumber(user.getLandNumber());
		dto.setGender(user.getGender());
		dto.setBirthDate(user.getBirthDate());
		dto.setAccountType(user.getAccountType());
		return dto;
	}
	
	public ProssedProjectInfoResponseDto createProjectInfoResponse(ProcessedProjectInfo processedData){
		ProssedProjectInfoResponseDto dto = new ProssedProjectInfoResponseDto();
		dto.setId(processedData.getId());
		dto.setProjectName(processedData.getProjectName());
		dto.setSharePrice(processedData.getSharePrice());
		dto.setImageUrl(processedData.getImageUrl());
		dto.setVideoUrl(processedData.getVideoUrl());
		dto.setFullAmmount(processedData.getFullAmmount());
		dto.setNoOfShares(processedData.getNoOfShares());
		dto.setMininumAmmount(processedData.getMininumAmmount());
		dto.setCurrency(processedData.getCurrency());
		dto.setCustomerType(processedData.getCustomerType());
		dto.setType(processedData.getType());
		dto.setCategory(processedData.getCategory());
		return dto;
	}
}
