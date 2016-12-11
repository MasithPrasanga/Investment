package com.investment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.investment.dto.CoreUserDto;
import com.investment.entity.CoreUser;
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
			CoreUser user = coreUserService.findByEmail(coreUserDto.getUserEmail());
			if(user != null){
				return new ResponseEntity<Void>(HttpStatus.ALREADY_REPORTED);
			}
			boolean status = logInHandler.createNewUser(coreUserDto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}catch(Exception e){
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
		}
		
	}

}












