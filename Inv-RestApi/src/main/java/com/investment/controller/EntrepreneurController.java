package com.investment.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

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
import com.investment.entity.RawData;
import com.investment.entity.Upload;
import com.investment.service.RawDataService;
import com.investment.service.UploadService;

@RestController
public class EntrepreneurController {

	@Autowired
	private RawDataService rawDataService = null;
	
	@Autowired
	private UploadService uploadService = null;

	// upload urls
	@RequestMapping(value = "/uploadurl", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadUrls(@RequestBody UploadedRawDataDto uploadedRawData) {
		
		try{
			List<String> urls = uploadedRawData.getUrls();
			
			RawData rawData = new RawData();
			rawData.setProjectName("ProjectName"+new Date());
			rawData.setDate(new Date());
			rawData.setAdminStatus("Not Approved");
			rawDataService.insert(rawData);
			
			for(String url: urls){
				Upload upload = new Upload();
				upload.setDate(new Date());
				upload.setUrl(url);
				upload.setRawData(rawData);
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
			rawDataService.insert(new RawData("Project " + i, "NOT APPROVED", new Date()));
		}
		return null;
	}

	// Get All Records (Working)
	@RequestMapping(value = "/getallrawdata", method = RequestMethod.GET)
	public ResponseEntity getAllRawData() {
		List<RawData> rawDataList = rawDataService.getAllRecords();
		if (rawDataList.isEmpty()) {
			return new ResponseEntity<List<RawData>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RawData>>(rawDataList, HttpStatus.OK);
	}

	// findById (Working)
	@RequestMapping(value = "/rawdata/{id}", method = RequestMethod.GET)
	public ResponseEntity<RawData> getUser(@PathVariable("id") long id) {
		RawData rawData = rawDataService.findById((int) id);
		if (rawData == null) {
			return new ResponseEntity<RawData>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RawData>(rawData, HttpStatus.OK);
	}

	// Update RawData (Working)
	@RequestMapping(value = "/rawdata/{id}", method = RequestMethod.PUT)
	public ResponseEntity<RawData> updateRawData(@PathVariable("id") long id, @RequestBody RawData rawData) {
		RawData currentRawData = rawDataService.findById((int) id);
		if (currentRawData == null) {
			return new ResponseEntity<RawData>(HttpStatus.NOT_FOUND);
		}
		currentRawData.setProjectName(rawData.getProjectName());
		currentRawData.setAdminStatus(rawData.getAdminStatus());
		currentRawData.setDate(rawData.getDate());
		rawDataService.update(currentRawData);
		return new ResponseEntity<RawData>(rawDataService.findById((int) id), HttpStatus.OK);
	}

	// Delete RawData (Not Working)
	@RequestMapping(value = "/deleterawdata/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<RawData> deleteRawData(@PathVariable("id") int id) {
		RawData rawData = rawDataService.findById(id);
		if (rawData == null) {
			return new ResponseEntity<RawData>(HttpStatus.NOT_FOUND);
		}
		rawDataService.delete(rawData);
		return new ResponseEntity<RawData>(HttpStatus.NO_CONTENT);
	}

	// Delete All Records (Not Working)
	@RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
	public ResponseEntity<RawData> deleteAllRawData() {
		rawDataService.deleteAllRecords();
		return new ResponseEntity<RawData>(HttpStatus.NO_CONTENT);
	}


}
