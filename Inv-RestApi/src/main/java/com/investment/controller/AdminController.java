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

import com.investment.dto.request.ProcessedProjectInfoDto;
import com.investment.dto.response.ProssedProjectInfoResponseDto;
import com.investment.dto.response.RawProjectInfoResponseDto;
import com.investment.dto.response.RawProposalResponseDto;
import com.investment.dto.response.root.RootResponse;
import com.investment.entity.BusinessUpload;
import com.investment.entity.ProcessedProjectInfo;
import com.investment.entity.RawProjectInfo;
import com.investment.handler.AdminHandler;
import com.investment.service.BusinessUploadService;
import com.investment.service.ProcessedProjectInfoService;
import com.investment.service.RawProjectInfoService;
import com.investment.util.ApiConstants;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

	@Autowired
	private AdminHandler adminHandler = null;

	@Autowired
	private RawProjectInfoService rawProjectInfoService = null;
	
	@Autowired
	private BusinessUploadService businessUploadService = null;

	@Autowired
	private ProcessedProjectInfoService processedProjectInfoService = null;

	// showing all the proposals to be approved
	@RequestMapping(value = "/newproposals", method = RequestMethod.GET)
	public ResponseEntity<List<RawProjectInfoResponseDto>> getAllNewlySubmitedProposals() {
		List<RawProjectInfo> rawProjectInfoList = rawProjectInfoService.getAllRecords();
		if (rawProjectInfoList.isEmpty()) {
			return new ResponseEntity<List<RawProjectInfoResponseDto>>(HttpStatus.NO_CONTENT);
		}
		List<RawProjectInfoResponseDto> responseList = new ArrayList<RawProjectInfoResponseDto>();
		RawProjectInfoResponseDto response = null;
		for (RawProjectInfo r : rawProjectInfoList) {
			if (r.getAdminStatus().equals(ApiConstants.ADMIN_NOT_APPROVED)) {
				response = adminHandler.createRawDataResponse(r);
				response.setCoreUser(adminHandler.createCoreUserResponse(r.getCoreUser()));
				responseList.add(response);	
			}
		}
		return new ResponseEntity<List<RawProjectInfoResponseDto>>(responseList, HttpStatus.OK);
	}

	// Get the Specific not approved added proposal details (Uploaded Data)
	@RequestMapping(value = "/singleproposaldetails/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<RawProposalResponseDto>> getSingleProposalDetails(
			@PathVariable("id") int rawProjectInfoId) {
		try {
			RawProjectInfo rawProjectInfo = rawProjectInfoService.findById(rawProjectInfoId);
			List<BusinessUpload> allBusinessUploadList = businessUploadService.findListById(rawProjectInfo);
			List<RawProposalResponseDto> responseList = new ArrayList<RawProposalResponseDto>();
			for (BusinessUpload b : allBusinessUploadList) {
				RawProposalResponseDto response = adminHandler.createSingleProposalDetails(b);			
				responseList.add(response);
			}
			return new ResponseEntity<List<RawProposalResponseDto>>(responseList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<RawProposalResponseDto>>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	// approving the proposal
	@RequestMapping(value = "/approveproposal", method = RequestMethod.POST)
	public ResponseEntity<RootResponse> saveProcessedData(
			@RequestBody ProcessedProjectInfoDto processedProjectInfoDto) {
		RootResponse response = new RootResponse();
		try {
			boolean status = adminHandler.createProject(processedProjectInfoDto);
			if (status) {
				response.setHttpStatus(HttpStatus.CREATED);
				return new ResponseEntity<RootResponse>(response, HttpStatus.CREATED);
			}
			response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
			return new ResponseEntity<RootResponse>(response, HttpStatus.EXPECTATION_FAILED);
		} catch (Exception e) {
			response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
			return new ResponseEntity<RootResponse>(response, HttpStatus.EXPECTATION_FAILED);
		}
	}

	// showing all the approved proposals
	@RequestMapping(value = "/approvedproposals", method = RequestMethod.GET)
	public ResponseEntity<List<ProssedProjectInfoResponseDto>> getAllApprovedProposals() {
		List<ProcessedProjectInfo> approvedProposalList = processedProjectInfoService.getAllRecords();
		List<ProssedProjectInfoResponseDto> responseList = new ArrayList<ProssedProjectInfoResponseDto>();
		try {
			if(approvedProposalList.isEmpty()){
				return new ResponseEntity<List<ProssedProjectInfoResponseDto>>(HttpStatus.NO_CONTENT);
			}
			ProssedProjectInfoResponseDto response = null;
			for(ProcessedProjectInfo data : approvedProposalList){
				response = adminHandler.createProjectInfoResponse(data);
				response.setEntrepreneur(adminHandler.createCoreUserResponse(data.getRawProjectInfo().getCoreUser()));
				responseList.add(response);
			}
			return new ResponseEntity<List<ProssedProjectInfoResponseDto>>(responseList,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<ProssedProjectInfoResponseDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}













