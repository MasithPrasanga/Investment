package com.investment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.investment.dto.CoreUserDto;
import com.investment.handler.LogInHandler;
import com.investment.service.CoreUserService;
import com.investment.service.UserRoleService;

@RestController
@RequestMapping("api/v1/user") 
public class LogInController {
	
	@Autowired
	private CoreUserService coreUserService = null;
	
	@Autowired 
	private UserRoleService userRoleService = null;
	
	@Autowired
	private LogInHandler logInHandler = null;
	
	// SignUp EndPoint
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadUrls(@RequestBody CoreUserDto coreUserDto) {
		
		try{
			// validate the user by email (to be developed )			
			boolean status = logInHandler.createNewUser(coreUserDto);
			
			return null;
		}catch(Exception e){
			return null;
		}
		
	}

}












