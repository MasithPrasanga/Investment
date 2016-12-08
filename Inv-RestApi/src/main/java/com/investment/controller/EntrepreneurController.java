package com.investment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.investment.dto.RawProjectInfoDto;
import com.investment.entity.RawProjectInfo;
import com.investment.handler.EntrepreneurHandler;
import com.investment.service.RawProjectInfoService;

@RestController
@RequestMapping("api/v1/entrepreneur")
public class EntrepreneurController {

	@Autowired
	private RawProjectInfoService rawProjectInfoService = null;

	@Autowired
	private EntrepreneurHandler entrepreneurHandler = null;

	// uploading files related to new proposal by entrepreneur
	@RequestMapping(value = "/uploadedmediaurls", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadUrls(@RequestBody RawProjectInfoDto uploadedRawData) {
		try {
			boolean status = entrepreneurHandler.createRawProjectInfo(uploadedRawData);
			if (status) {
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			}
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	// showing all his proposals to entrepreneur
	@RequestMapping(value = "/allproposals/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<RawProjectInfo>> getAllProposals(@PathVariable("id") int userid) {
		try {
			List<RawProjectInfo> rawProjectInfoList = rawProjectInfoService.findByUserId(userid);
			if (rawProjectInfoList.isEmpty()) {
				return new ResponseEntity<List<RawProjectInfo>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<RawProjectInfo>>(rawProjectInfoList, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Get All Proposals Exception : "+e);
			return new ResponseEntity<List<RawProjectInfo>>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	
}
