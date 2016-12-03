package com.investment.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.investment.dto.UploadedRawDataDto;
import com.investment.entity.RawProjectInfo;
import com.investment.entity.Upload;
import com.investment.service.RawProjectInfoService;
import com.investment.service.UploadService;

@RestController
public class EntrepreneurController {

	@Autowired
	private RawProjectInfoService rawProjectInfoService = null;
	
	@Autowired
	private UploadService uploadService = null;

	// upload urls
	@RequestMapping(value = "/uploadurl", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadUrls(@RequestBody UploadedRawDataDto uploadedRawData) {
		
		try{
			List<String> urls = uploadedRawData.getUrls();
			RawProjectInfo rawProjectInfo = new RawProjectInfo();
			rawProjectInfo.setProjectName("ProjectName"+new Date());
			rawProjectInfo.setDate(new Date());
			rawProjectInfo.setAdminStatus("Not Approved");
			rawProjectInfoService.insert(rawProjectInfo);
			
			for(String url: urls){
				Upload upload = new Upload();
				upload.setDate(new Date());
				upload.setUrl(url);
				upload.setRawData(rawProjectInfo);
				uploadService.insert(upload);
				
			}
			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			
		}catch(Exception e){
			return null;
		}
		
	}

	// Save Raw Data (Working)
	@RequestMapping(value = "/saverawdata", method = RequestMethod.POST)
	public ResponseEntity<Void> saveRawData() {
		for (int i = 1; i <= 10; i++) {
			rawProjectInfoService.insert(new RawProjectInfo("Project " + i, "NOT APPROVED", new Date()));
		}
		return null;
	}

	// Get All Records (Working)
	@RequestMapping(value = "/getallrawdata", method = RequestMethod.GET)
	public ResponseEntity getAllRawData() {
		List<RawProjectInfo> rawProjectInfoList = rawProjectInfoService.getAllRecords();
		if (rawProjectInfoList.isEmpty()) {
			return new ResponseEntity<List<RawProjectInfo>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RawProjectInfo>>(rawProjectInfoList, HttpStatus.OK);
	}

	// findById (Working)
	@RequestMapping(value = "/rawdata/{id}", method = RequestMethod.GET)
	public ResponseEntity<RawProjectInfo> getUser(@PathVariable("id") long id) {
		RawProjectInfo rawProjectInfo = rawProjectInfoService.findById((int) id);
		if (rawProjectInfo == null) {
			return new ResponseEntity<RawProjectInfo>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RawProjectInfo>(rawProjectInfo, HttpStatus.OK);
	}

	// Update RawData (Working)
	@RequestMapping(value = "/rawdata/{id}", method = RequestMethod.PUT)
	public ResponseEntity<RawProjectInfo> updateRawData(@PathVariable("id") long id, @RequestBody RawProjectInfo rawProjectInfo) {
		RawProjectInfo currentRawProjectInfo = rawProjectInfoService.findById((int) id);
		if (currentRawProjectInfo == null) {
			return new ResponseEntity<RawProjectInfo>(HttpStatus.NOT_FOUND);
		}
		currentRawProjectInfo.setProjectName(rawProjectInfo.getProjectName());
		currentRawProjectInfo.setAdminStatus(rawProjectInfo.getAdminStatus());
		currentRawProjectInfo.setDate(rawProjectInfo.getDate());
		rawProjectInfoService.update(currentRawProjectInfo);
		return new ResponseEntity<RawProjectInfo>(rawProjectInfoService.findById((int) id), HttpStatus.OK);
	}

	// Delete RawData (Not Working)
	@RequestMapping(value = "/deleterawdata/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<RawProjectInfo> deleteRawData(@PathVariable("id") int id) {
		RawProjectInfo rawProjectInfo = rawProjectInfoService.findById(id);
		if (rawProjectInfo == null) {
			return new ResponseEntity<RawProjectInfo>(HttpStatus.NOT_FOUND);
		}
		rawProjectInfoService.delete(rawProjectInfo);
		return new ResponseEntity<RawProjectInfo>(HttpStatus.NO_CONTENT);
	}

	// Delete All Records (Not Working)
	@RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
	public ResponseEntity<RawProjectInfo> deleteAllRawData() {
		rawProjectInfoService.deleteAllRecords();
		return new ResponseEntity<RawProjectInfo>(HttpStatus.NO_CONTENT);
	}


}
