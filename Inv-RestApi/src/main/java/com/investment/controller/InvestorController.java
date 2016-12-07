package com.investment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.investment.entity.ProcessedProjectInfo;
import com.investment.service.ProcessedProjectInfoService;

@RestController
@RequestMapping("api/v1/investor")
public class InvestorController {

	@Autowired
	private ProcessedProjectInfoService processedProjectInfoService = null;
	
	// Show All the Approved project List after sign in 
	@RequestMapping(value = "/getnewprojects", method = RequestMethod.GET)
	public ResponseEntity<List<ProcessedProjectInfo>> getAllNewProjects() {
		List<ProcessedProjectInfo> approvedProposalList = processedProjectInfoService.getAllRecords();
		if (approvedProposalList.isEmpty()) {
			return new ResponseEntity<List<ProcessedProjectInfo>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProcessedProjectInfo>>(approvedProposalList, HttpStatus.OK);
	}
}

