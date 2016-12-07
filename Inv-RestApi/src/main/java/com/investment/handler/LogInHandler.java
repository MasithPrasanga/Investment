package com.investment.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investment.dto.CoreUserDto;
import com.investment.entity.CoreUser;
import com.investment.entity.UserRole;
import com.investment.service.CoreUserService;
import com.investment.service.UserRoleService;
import com.investment.util.ApiConstants;

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
			int userid = (int)coreUserService.insert(user);
			
			System.out.println("Saved User : "+coreUserService.findById(userid));
			// send the email to the Admin that new user as registered and himself also
			UserRole userRole = new UserRole();
			userRole.setAccessType(ApiConstants.ADMIN_ACCESS);
			userRole.setCoreUser(coreUserService.findById(userid));
			userRoleService.insert(userRole);
			return true;
			
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
		
	}
}













