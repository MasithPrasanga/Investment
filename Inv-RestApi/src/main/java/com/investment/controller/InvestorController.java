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

import com.investment.dto.request.InvestRequestDto;
import com.investment.dto.response.InvestmentResponseDto;
import com.investment.dto.response.ProssedProjectInfoResponseDto;
import com.investment.entity.ProcessedProjectInfo;
import com.investment.handler.InvestorHandler;
import com.investment.service.InvestmentService;
import com.investment.service.ProcessedProjectInfoService;
import com.investment.util.ApiConstants;

@RestController
@RequestMapping("api/v1/investor")
public class InvestorController {
	
	@Autowired
	private InvestmentService investmentService = null;

	@Autowired
	private InvestorHandler investorHandler = null;
	
	
	@Autowired
	private ProcessedProjectInfoService processedProjectInfoService = null;

	// Show All the Approved project List to investor after sign in
	@RequestMapping(value = "/getnewproposals", method = RequestMethod.GET)
	public ResponseEntity<List<ProssedProjectInfoResponseDto>> getAllNewProjects() {

		List<ProcessedProjectInfo> processedProjectInfoList = processedProjectInfoService.getAllRecords();
		if (processedProjectInfoList.isEmpty()) {
			return new ResponseEntity<List<ProssedProjectInfoResponseDto>>(HttpStatus.NO_CONTENT);
		}
		List<ProssedProjectInfoResponseDto> prossedProjectInfoResponseList = new ArrayList<ProssedProjectInfoResponseDto>();
		for (ProcessedProjectInfo p : processedProjectInfoList) {
			ProssedProjectInfoResponseDto processedProjectInfoResponse = new ProssedProjectInfoResponseDto();
			processedProjectInfoResponse.setId(p.getId());
			processedProjectInfoResponse.setProjectName(p.getProjectName());
			processedProjectInfoResponse.setSharePrice(p.getSharePrice());
			processedProjectInfoResponse.setImageUrl(p.getImageUrl());
			processedProjectInfoResponse.setVideoUrl(p.getVideoUrl());

			processedProjectInfoResponse.setFullAmmount(p.getFullAmmount());
			processedProjectInfoResponse.setNoOfShares(p.getNoOfShares());
			processedProjectInfoResponse.setMininumAmmount(p.getMininumAmmount());
			processedProjectInfoResponse.setType(p.getType());
			processedProjectInfoResponse.setCurrency(p.getCurrency());
			processedProjectInfoResponse.setCategory(p.getCategory());
			processedProjectInfoResponse.setCustomerType(p.getCustomerType());
			processedProjectInfoResponse.setCoreUser(p.getRawProjectInfo().getCoreUser());
			prossedProjectInfoResponseList.add(processedProjectInfoResponse);
		}

		return new ResponseEntity<List<ProssedProjectInfoResponseDto>>(prossedProjectInfoResponseList, HttpStatus.OK);
	}

	// Investing on a project
	@RequestMapping(value = "/invest", method = RequestMethod.POST)
	public ResponseEntity<InvestmentResponseDto> invest(@RequestBody InvestRequestDto investmentRequest) {
		InvestmentResponseDto investmentResponse = new InvestmentResponseDto();
		try {
			long id = investmentService.insert(investorHandler.makeInvestment(investmentRequest));
			if(id == ApiConstants.PERSISTED_EXCEPTION){
				investmentResponse.setStatus(HttpStatus.EXPECTATION_FAILED);
				return new ResponseEntity<InvestmentResponseDto>(investmentResponse,HttpStatus.EXPECTATION_FAILED);
			}
			investmentResponse.setStatus(HttpStatus.CREATED);
			return new ResponseEntity<InvestmentResponseDto>(investmentResponse,HttpStatus.CREATED);	
		} catch (Exception e) {
			investmentResponse.setStatus(HttpStatus.EXPECTATION_FAILED);
			return new ResponseEntity<InvestmentResponseDto>(investmentResponse,HttpStatus.EXPECTATION_FAILED);
		}
	}

	// Show All the Funded Project List (To be Implemented)
	@RequestMapping(value = "/getfundedprojects", method = RequestMethod.GET)
	public ResponseEntity<List<ProcessedProjectInfo>> getAllFundedProjects() {
		return new ResponseEntity<List<ProcessedProjectInfo>>(HttpStatus.NO_CONTENT);
	}
}














