package com.investment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.investment.dto.ProcessedProjectInfoDto;
import com.investment.dto.response.RawProjectInfoResponseDto;
import com.investment.dto.response.RawProposalResponseDto;
import com.investment.entity.BusinessUpload;
import com.investment.entity.RawProjectInfo;
import com.investment.handler.AdminHandler;
import com.investment.service.BusinessUploadService;
import com.investment.service.RawProjectInfoService;
import com.investment.util.ApiConstants;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

	@Autowired
	private BusinessUploadService businessUploadService = null;

	@Autowired
	private RawProjectInfoService rawProjectInfoService = null;
	
	@Autowired
	private AdminHandler adminHandler = null;

	// processing the newly added proposals and approving it
	@RequestMapping(value = "/approveproposal", method = RequestMethod.POST)
	public ResponseEntity<Void> saveProcessedData(@RequestBody ProcessedProjectInfoDto processedProjectInfoDto) {
		try {
			boolean status = adminHandler.createProject(processedProjectInfoDto);
			if (status) {
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			}
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	// showing the newly submitted proposals to the admin
	@RequestMapping(value = "/newproposals", method = RequestMethod.GET)
	public ResponseEntity<List<RawProjectInfoResponseDto>> getAllNewlySubmitedProposals() {

		List<RawProjectInfo> rawProjectInfoList = rawProjectInfoService.getAllRecords();

		if (rawProjectInfoList.isEmpty()) {
			return new ResponseEntity<List<RawProjectInfoResponseDto>>(HttpStatus.NO_CONTENT);
		}

		List<RawProjectInfoResponseDto> rawProjectInfoResponseList = new ArrayList<RawProjectInfoResponseDto>();
		for (RawProjectInfo r : rawProjectInfoList) {
			if (r.getAdminStatus().equals(ApiConstants.ADMIN_NOT_APPROVED)) {
				RawProjectInfoResponseDto rawProjectInfoResponse = new RawProjectInfoResponseDto();
				rawProjectInfoResponse.setId(r.getId());
				rawProjectInfoResponse.setProjectName(r.getProjectName());
				rawProjectInfoResponse.setSubmitedDate(r.getSubmitedDate());
				rawProjectInfoResponse.setCoreUser(r.getCoreUser());
				rawProjectInfoResponseList.add(rawProjectInfoResponse);
			}
		}

		return new ResponseEntity<List<RawProjectInfoResponseDto>>(rawProjectInfoResponseList, HttpStatus.OK);
	}

	// Get the Specific newly added proposal details (try to develop with using newly written findListById service method)
	@RequestMapping(value = "/singleproposaldetails/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<RawProposalResponseDto>> getSingleProposalDetails(@PathVariable("id") int rawProjectInfoId) {
		
		try{
			RawProjectInfo rawProjectInfo = rawProjectInfoService.findById(rawProjectInfoId);
			List<BusinessUpload> allBusinessUploadList = businessUploadService.findListById(rawProjectInfo);
			List<RawProposalResponseDto> rawProposalResponseList = new ArrayList<RawProposalResponseDto>();
			for(BusinessUpload b :allBusinessUploadList){
				RawProposalResponseDto rawProposalResponseDto = new RawProposalResponseDto();
				rawProposalResponseDto.setId(b.getId());
				rawProposalResponseDto.setDate(b.getDate());
				rawProposalResponseDto.setUrl(b.getUrl());
				rawProposalResponseList.add(rawProposalResponseDto);
			}
			return new ResponseEntity<List<RawProposalResponseDto>>(rawProposalResponseList,HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<List<RawProposalResponseDto>>(HttpStatus.EXPECTATION_FAILED);
		}
	}
}






























