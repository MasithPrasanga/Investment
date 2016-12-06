package com.investment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.investment.dto.RawProjectInfoDto;
import com.investment.service.CoreUserService;
import com.investment.service.UserRoleService;

@RestController
@RequestMapping("api/v1/user") 
public class LogInController {
	
	@Autowired
	private CoreUserService coreUserService = null;
	
	@Autowired 
	private UserRoleService userRoleService = null;
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadUrls() {
		
		System.out.println("Inside the Sigup Controller");
		
		try{
			return null;
		}catch(Exception e){
			return null;
		}
		
	}

}












