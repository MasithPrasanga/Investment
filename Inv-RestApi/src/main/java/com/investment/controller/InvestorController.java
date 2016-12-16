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

import com.investment.dto.request.InvestRequestDto;
import com.investment.dto.response.CoreUserResponseDto;
import com.investment.dto.response.InvestedProjectsResponseDto;
import com.investment.dto.response.InvestmentResponseDto;
import com.investment.dto.response.ProssedProjectInfoResponseDto;
import com.investment.entity.CoreUser;
import com.investment.entity.Investment;
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
			CoreUserResponseDto entrepreneur = investorHandler.createCoreUserResponse(p.getRawProjectInfo().getCoreUser());
			processedProjectInfoResponse.setCoreUser(entrepreneur);
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
				investmentResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
				return new ResponseEntity<InvestmentResponseDto>(investmentResponse,HttpStatus.EXPECTATION_FAILED);
			}
			investmentResponse.setHttpStatus(HttpStatus.CREATED);
			return new ResponseEntity<InvestmentResponseDto>(investmentResponse,HttpStatus.CREATED);	
		} catch (Exception e) {
			investmentResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
			return new ResponseEntity<InvestmentResponseDto>(investmentResponse,HttpStatus.EXPECTATION_FAILED);
		}
	}

	// Show All the invested Project List 
	@RequestMapping(value = "/investedprojects/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<InvestedProjectsResponseDto>> getInvestedProjects(@PathVariable("id") int investorId) {
		try{
			List<Investment> investmentList = investmentService.findByInvestorId(investorId);
			List<InvestedProjectsResponseDto> investedProjectList = new ArrayList<InvestedProjectsResponseDto>();
			for(Investment inv : investmentList){
				InvestedProjectsResponseDto investedProject = new InvestedProjectsResponseDto();
				investedProject.setProjectName(inv.getProcessedProject().getProjectName());
				investedProject.setSingleSharePrice(inv.getProcessedProject().getSharePrice());
				investedProject.setImageUrl(inv.getProcessedProject().getImageUrl());
				investedProject.setVideoUrl(inv.getProcessedProject().getVideoUrl());
				investedProject.setFullAmmountCanInvest(inv.getProcessedProject().getFullAmmount());
				investedProject.setNoOfSharesCanBuy(inv.getProcessedProject().getNoOfShares());
				investedProject.setMininumAmmountCanInvest(inv.getProcessedProject().getMininumAmmount());
				investedProject.setType(inv.getProcessedProject().getType().getType());
				investedProject.setCurrency(inv.getProcessedProject().getCurrency().getName());
				investedProject.setCustomerType(inv.getProcessedProject().getCustomerType().getType());
				investedProject.setCategory(inv.getProcessedProject().getCategory().getName());
				investedProject.setInvestedDate(inv.getInvestedDate());
				investedProject.setInvestedAmouont(inv.getInvestedAmouont());
				investedProject.setInvestedNoOfShares(inv.getNoOfShares());
				investedProject.setInvestedPrecentage(inv.getPresentageOfFullAmount());
				
				CoreUserResponseDto entrepreneur = new CoreUserResponseDto();
				CoreUser user = inv.getProcessedProject().getRawProjectInfo().getCoreUser();
				entrepreneur = investorHandler.createCoreUserResponse(user);
				investedProject.setEntrepreneur(entrepreneur);
				
				investedProjectList.add(investedProject);
			}
			
			return new ResponseEntity<List<InvestedProjectsResponseDto>>(investedProjectList,HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<InvestedProjectsResponseDto>>(HttpStatus.EXPECTATION_FAILED);
		}
	}
}




















