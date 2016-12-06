package com.investment.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investment.dto.CoreUserDto;
import com.investment.entity.CoreUser;
import com.investment.service.CoreUserService;
import com.investment.service.UserRoleService;

@Component
public class LogInHandler {

	@Autowired
	private CoreUserService coreUserService = null;
	
	private UserRoleService userRoleService = null;
	
	public boolean createNewUser(CoreUserDto coreUserDto){
		
		try{
			CoreUser user = new CoreUser();
			user.setFirstName(coreUserDto.getFirstName());
			user.setLastName(coreUserDto.getLastName());
			user.setUserEmail(coreUserDto.getUserEmail());
			user.setPassword(coreUserDto.getPassword());
			user.setMobileNumber(coreUserDto.getMobileNumber());
			user.setLandNumber(coreUserDto.getLandNumber());
			user.setBirthDate(coreUserDto.getBirthDate());
			user.setGender(coreUserDto.getGender());
			user.setAccountType(coreUserDto.getAccountType());
			user.setCreatedDate(coreUserDto.getCreatedDate());
			user.setActivationStatus(coreUserDto.getActivationStatus());
			long inserted = coreUserService.insert(user);
			if(inserted != -1){
				// send the email to the admin that new user as registered
				return true;
			}	
			
		}catch(Exception e){
			return false;
		}
		
		return false;
	}
}













