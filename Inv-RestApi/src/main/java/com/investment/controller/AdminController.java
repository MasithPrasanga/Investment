package com.investment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.investment.dto.ProcessedProjectInfoDto;
import com.investment.handler.AdminHandler;
import com.investment.service.CoreUserService;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

	@Autowired
	private CoreUserService coreUserService = null;
	
	@Autowired
	private AdminHandler adminHandler = null;

	/*// approving the account
	@RequestMapping(value = "/approvesingup", method = RequestMethod.POST)
	public ResponseEntity<Void> approveAccount() {
		System.out.println("Inside the approve account method");
		return null;
	}*/


   /* // Get Not Approved Sign Up Requests
	@RequestMapping(value = "/singuprequests", method = RequestMethod.GET)
	public ResponseEntity<List<CoreUser>> getAllSinnUpRequests() {
		List<CoreUser> allCoreUserList = coreUserService.getAllRecords();
		List<CoreUser> notApprovedList = new ArrayList<CoreUser>();
		if (allCoreUserList.isEmpty()) {
			return new ResponseEntity<List<CoreUser>>(HttpStatus.NO_CONTENT);
		} else {
			for (CoreUser user : allCoreUserList) {
				if (user.getActivationStatus().equals(ApiConstants.AdminNotApproved)) {
					notApprovedList.add(user);
				}
			}
			return new ResponseEntity<List<CoreUser>>(notApprovedList, HttpStatus.OK);
		}

	}
*/
	// save currency
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Void> saveProcessedData(@RequestBody ProcessedProjectInfoDto processedProjectInfoDto) {

		try{
			
			adminHandler.createProject(processedProjectInfoDto);	
			
		}catch(Exception e){
			
		}

		return null;
	}

}



















