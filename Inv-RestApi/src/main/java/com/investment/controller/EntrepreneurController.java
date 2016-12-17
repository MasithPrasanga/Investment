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

import com.investment.dto.request.RawProjectInfoRequestDto;
import com.investment.dto.response.CoreUserResponseDto;
import com.investment.dto.response.EntrepreneurProposalsResponseDto;
import com.investment.dto.response.ProssedProjectInfoResponseDto;
import com.investment.dto.response.RawProjectInfoResponseDto;
import com.investment.dto.response.root.RootResponse;
import com.investment.entity.RawProjectInfo;
import com.investment.handler.EntrepreneurHandler;
import com.investment.service.CoreUserService;
import com.investment.service.RawProjectInfoService;
import com.investment.util.ApiConstants;

@RestController
@RequestMapping("api/v1/entrepreneur")
public class EntrepreneurController {

	@Autowired
	private RawProjectInfoService rawProjectInfoService = null;

	@Autowired
	private CoreUserService coreUserService = null;

	@Autowired
	private EntrepreneurHandler entrepreneurHandler = null;

	// uploading files related to new proposal by entrepreneur
	@RequestMapping(value = "/uploadedmediaurls", method = RequestMethod.POST)
	public ResponseEntity<RootResponse> uploadUrls(@RequestBody RawProjectInfoRequestDto uploadedRawData) {
		RootResponse response = new RootResponse();
		try {
			boolean status = entrepreneurHandler.createRawProjectInfo(uploadedRawData);
			if (status) {
				response.setHttpStatus(HttpStatus.CREATED);
				return new ResponseEntity<RootResponse>(response,HttpStatus.CREATED);
			}
			response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
			return new ResponseEntity<RootResponse>(response,HttpStatus.EXPECTATION_FAILED);
		} catch (Exception e) {
			response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<RootResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// showing all his proposals to entrepreneur
	@RequestMapping(value = "/allprojects/{id}", method = RequestMethod.POST)
	public ResponseEntity<EntrepreneurProposalsResponseDto> getAllProposals(@PathVariable("id") int userid) {

		EntrepreneurProposalsResponseDto entrepreneurProjectsDto = new EntrepreneurProposalsResponseDto();
		
		try {	
			
			List<RawProjectInfoResponseDto> rawProjectInfoResponseDtoList = new ArrayList<RawProjectInfoResponseDto>();
			List<ProssedProjectInfoResponseDto> prossedProjectInfoResponseDtoList = new ArrayList<ProssedProjectInfoResponseDto>();
			List<RawProjectInfo> rawProjectInfoList = rawProjectInfoService.findByUserId(userid);
			
			if (rawProjectInfoList.isEmpty()) {
				entrepreneurProjectsDto.setHttpStatus(HttpStatus.NO_CONTENT);
				return new ResponseEntity<EntrepreneurProposalsResponseDto>(entrepreneurProjectsDto,HttpStatus.NO_CONTENT);
			}

			CoreUserResponseDto user = entrepreneurHandler.createCoreUserResponse(coreUserService.findById(userid));
			entrepreneurProjectsDto.setEntrepreneur(user);

			for (RawProjectInfo r : rawProjectInfoList) {
				if (r.getAdminStatus().equals(ApiConstants.ADMIN_NOT_APPROVED)) {
					rawProjectInfoResponseDtoList.add(entrepreneurHandler.createRawProjectResponse(r));			
				} else if (r.getAdminStatus().equals(ApiConstants.ADMIN_APPROVED)) {
					prossedProjectInfoResponseDtoList.add(entrepreneurHandler.createProcessedProjectResponse(r));
				}
			}
			
			entrepreneurProjectsDto.setHttpStatus(HttpStatus.OK);
			entrepreneurProjectsDto.setRawProjectList(rawProjectInfoResponseDtoList);
			entrepreneurProjectsDto.setProcessedProjectList(prossedProjectInfoResponseDtoList);

			return new ResponseEntity<EntrepreneurProposalsResponseDto>(entrepreneurProjectsDto, HttpStatus.OK);

		} catch (Exception e) {
			entrepreneurProjectsDto.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<EntrepreneurProposalsResponseDto>(entrepreneurProjectsDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}








