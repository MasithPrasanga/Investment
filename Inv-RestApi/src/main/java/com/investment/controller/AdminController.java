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
import com.investment.dto.response.RawProjectInfoResponseDto;
import com.investment.entity.BusinessUpload;
import com.investment.entity.RawProjectInfo;
import com.investment.handler.AdminHandler;
import com.investment.service.BusinessUploadService;
import com.investment.service.CoreUserService;
import com.investment.service.RawProjectInfoService;
import com.investment.util.ApiConstants;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

	@Autowired
	private CoreUserService coreUserService = null;
	
	@Autowired
	private BusinessUploadService businessUploadService= null;
	
	@Autowired
	private AdminHandler adminHandler = null;
	
	@Autowired
	private RawProjectInfoService rawProjectInfoService = null;

	// processing the newly added proposals and approving it
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Void> saveProcessedData(@RequestBody ProcessedProjectInfoDto processedProjectInfoDto) {
		try{
			boolean status = adminHandler.createProject(processedProjectInfoDto);
			if(status){
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			}
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);	
		}catch(Exception e){
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	// showing the newly submitted proposals to the admin (This end point is very slow)
	@RequestMapping(value = "/newproposals", method = RequestMethod.GET)
	public ResponseEntity<List<RawProjectInfoResponseDto>> getAllNewlySubmitedProposals() {

		List<RawProjectInfo> rawProjectInfoList = rawProjectInfoService.getAllRecords();
		
		if (rawProjectInfoList.isEmpty()) {
			return new ResponseEntity<List<RawProjectInfoResponseDto>>(HttpStatus.NO_CONTENT);
		}
		
		List<RawProjectInfoResponseDto> rawProjectInfoResponseList = new ArrayList<RawProjectInfoResponseDto>();
		for(RawProjectInfo r: rawProjectInfoList){
			if(r.getAdminStatus().equals(ApiConstants.ADMIN_NOT_APPROVED)){
				RawProjectInfoResponseDto rawProjectInfoResponseDto = new RawProjectInfoResponseDto();
				rawProjectInfoResponseDto.setId(r.getId());
				rawProjectInfoResponseDto.setProjectName(r.getProjectName());
				rawProjectInfoResponseDto.setSubmitedDate(r.getSubmitedDate());
				rawProjectInfoResponseDto.setCoreUser(r.getCoreUser());
				rawProjectInfoResponseList.add(rawProjectInfoResponseDto);
			}
		}
		
		return new ResponseEntity<List<RawProjectInfoResponseDto>>(rawProjectInfoResponseList, HttpStatus.OK);
	}
	
	// Get the Specific newly added proposal details
	
}



















