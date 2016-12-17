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
import com.investment.handler.InvestorHandler;
import com.investment.service.InvestmentService;

@RestController
@RequestMapping("api/v1/investor")
public class InvestorController {

	@Autowired
	private InvestorHandler investorHandler = null;

	@Autowired
	private InvestmentService investmentService = null;

	// Show All the Approved project List to investor after sign in
	@RequestMapping(value = "/getnewproposals/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<ProssedProjectInfoResponseDto>> getAllNewProjects(@PathVariable("id") int investorId) {
		try{
			List<ProssedProjectInfoResponseDto> responseList = investorHandler.getProjectsToBeInvested(investorId);
			if(responseList.isEmpty()){
				return new ResponseEntity<List<ProssedProjectInfoResponseDto>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<ProssedProjectInfoResponseDto>>(responseList,HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<ProssedProjectInfoResponseDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Investing on a project
	@RequestMapping(value = "/invest", method = RequestMethod.POST)
	public ResponseEntity<InvestmentResponseDto> invest(@RequestBody InvestRequestDto investmentRequest) {
		InvestmentResponseDto investmentResponse = new InvestmentResponseDto();
		try {
			boolean status = investorHandler.makeInvestment(investmentRequest);
			if (status) {
				investmentResponse.setHttpStatus(HttpStatus.CREATED);
				return new ResponseEntity<InvestmentResponseDto>(investmentResponse, HttpStatus.CREATED);
			}
			investmentResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
			return new ResponseEntity<InvestmentResponseDto>(investmentResponse, HttpStatus.EXPECTATION_FAILED);
		} catch (Exception e) {
			investmentResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<InvestmentResponseDto>(investmentResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Show All the invested Project List
	@RequestMapping(value = "/investedprojects/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<InvestedProjectsResponseDto>> getInvestedProjects(@PathVariable("id") int investorId) {
		try {
			List<Investment> investmentList = investmentService.findByInvestorId(investorId);
			List<InvestedProjectsResponseDto> responseList = new ArrayList<InvestedProjectsResponseDto>();

			for (Investment inv : investmentList) {
				InvestedProjectsResponseDto response = investorHandler.createInvestedProjectResponse(inv);
				CoreUserResponseDto entrepreneur = new CoreUserResponseDto();
				CoreUser user = inv.getProcessedProject().getRawProjectInfo().getCoreUser();
				entrepreneur = investorHandler.createCoreUserResponse(user);
				response.setEntrepreneur(entrepreneur);
				responseList.add(response);
			}
			return new ResponseEntity<List<InvestedProjectsResponseDto>>(responseList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<InvestedProjectsResponseDto>>(HttpStatus.EXPECTATION_FAILED);
		}
	}
}
