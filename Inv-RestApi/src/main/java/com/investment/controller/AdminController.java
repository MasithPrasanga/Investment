package com.investment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.investment.dto.ProcessedProjectInfoDto;
import com.investment.entity.RawProjectInfo;
import com.investment.handler.AdminHandler;
import com.investment.service.CoreUserService;
import com.investment.service.RawProjectInfoService;
import com.investment.util.ApiConstants;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

	@Autowired
	private CoreUserService coreUserService = null;
	
	@Autowired
	private AdminHandler adminHandler = null;
	
	@Autowired
	private RawProjectInfoService rawProjectInfoService = null;

	// Saving the processed project info details
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Void> saveProcessedData(@RequestBody ProcessedProjectInfoDto processedProjectInfoDto) {
		try{
			adminHandler.createProject(processedProjectInfoDto);	
		}catch(Exception e){
			
		}
		return null;
	}

	// showing the newly submitted proposals to the admin
	@RequestMapping(value = "/newproposals", method = RequestMethod.GET)
	public ResponseEntity<List<RawProjectInfo>> getAllNewlySubmitedProposals() {
		
		List<RawProjectInfo> rawProjectInfoList = rawProjectInfoService.getAllRecords();
		List<RawProjectInfo> notApprovedList = new ArrayList<RawProjectInfo>();
		
		if (rawProjectInfoList.isEmpty()) {
			return new ResponseEntity<List<RawProjectInfo>>(HttpStatus.NO_CONTENT);
		}
		
		for(RawProjectInfo rawProjectInfo :rawProjectInfoList){
			if(rawProjectInfo.getAdminStatus().equals(ApiConstants.ADMIN_NOT_APPROVED)){
				notApprovedList.add(rawProjectInfo);
			}	
		}
		return new ResponseEntity<List<RawProjectInfo>>(notApprovedList, HttpStatus.OK);
	}
	
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
	
}



















