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
	private BusinessUploadService businessUploadService = null;

	@Autowired
	private RawProjectInfoService rawProjectInfoService = null;

	@Autowired
	private ProcessedProjectInfoService processedProjectInfoService = null;

	// showing all the proposals to be approved
	@RequestMapping(value = "/newproposals", method = RequestMethod.GET)
	public ResponseEntity<List<RawProjectInfoResponseDto>> getAllNewlySubmitedProposals() {

		List<RawProjectInfo> rawProjectInfoList = rawProjectInfoService.getAllRecords();

		if (rawProjectInfoList.isEmpty()) {
			return new ResponseEntity<List<RawProjectInfoResponseDto>>(HttpStatus.NO_CONTENT);
		}

		List<RawProjectInfoResponseDto> rawProjectInfoResponseList = new ArrayList<RawProjectInfoResponseDto>();
		RawProjectInfoResponseDto rawProjectInfoResponse = null;
		for (RawProjectInfo r : rawProjectInfoList) {
			if (r.getAdminStatus().equals(ApiConstants.ADMIN_NOT_APPROVED)) {
				rawProjectInfoResponse = new RawProjectInfoResponseDto();
				rawProjectInfoResponse.setId(r.getId());
				rawProjectInfoResponse.setProjectName(r.getProjectName());
				rawProjectInfoResponse.setSubmitedDate(r.getSubmitedDate());
				rawProjectInfoResponse.setCoreUser(r.getCoreUser());
				rawProjectInfoResponseList.add(rawProjectInfoResponse);
			}
		}

		return new ResponseEntity<List<RawProjectInfoResponseDto>>(rawProjectInfoResponseList, HttpStatus.OK);
	}

	// Get the Specific not approved added proposal details (Uploaded Data)
	@RequestMapping(value = "/singleproposaldetails/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<RawProposalResponseDto>> getSingleProposalDetails(
			@PathVariable("id") int rawProjectInfoId) {

		try {
			RawProjectInfo rawProjectInfo = rawProjectInfoService.findById(rawProjectInfoId);
			List<BusinessUpload> allBusinessUploadList = businessUploadService.findListById(rawProjectInfo);
			List<RawProposalResponseDto> rawProposalResponseList = new ArrayList<RawProposalResponseDto>();
			for (BusinessUpload b : allBusinessUploadList) {
				RawProposalResponseDto rawProposalResponseDto = new RawProposalResponseDto();
				rawProposalResponseDto.setId(b.getId());
				rawProposalResponseDto.setDate(b.getDate());
				rawProposalResponseDto.setUrl(b.getUrl());
				rawProposalResponseList.add(rawProposalResponseDto);
			}
			return new ResponseEntity<List<RawProposalResponseDto>>(rawProposalResponseList, HttpStatus.OK);

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
				response = new ProssedProjectInfoResponseDto();
				response.setProjectName(data.getProjectName());
				response.setSharePrice(data.getSharePrice());
				response.setImageUrl(data.getImageUrl());
				response.setVideoUrl(data.getVideoUrl());
				response.setNoOfShares(data.getNoOfShares());
				response.setMininumAmmount(data.getMininumAmmount());
				response.setCoreUser(adminHandler.createCoreUserResponse(data.getRawProjectInfo().getCoreUser()));
				response.setCurrency(data.getCurrency());
				response.setCustomerType(data.getCustomerType());
				response.setType(data.getType());
				response.setCategory(data.getCategory());
				responseList.add(response);
			}
			
			return new ResponseEntity<List<ProssedProjectInfoResponseDto>>(responseList,HttpStatus.OK);
				
		} catch (Exception e) {
			return new ResponseEntity<List<ProssedProjectInfoResponseDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}













