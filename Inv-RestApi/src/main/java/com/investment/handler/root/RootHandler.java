package com.investment.handler.root;

import com.investment.dto.response.CoreUserResponseDto;
import com.investment.entity.CoreUser;

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
}
