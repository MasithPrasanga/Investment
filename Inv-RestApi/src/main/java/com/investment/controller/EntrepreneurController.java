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

import com.investment.dto.RawProjectInfoDto;
import com.investment.dto.response.EntrepreneurProjectsDto;
import com.investment.dto.response.ProssedProjectInfoResponseDto;
import com.investment.dto.response.RawProjectInfoResponseDto;
import com.investment.entity.ProcessedProjectInfo;
import com.investment.entity.RawProjectInfo;
import com.investment.handler.EntrepreneurHandler;
import com.investment.service.CoreUserService;
import com.investment.service.ProcessedProjectInfoService;
import com.investment.service.RawProjectInfoService;
import com.investment.util.ApiConstants;

@RestController
@RequestMapping("api/v1/entrepreneur")
public class EntrepreneurController {

	@Autowired
	private RawProjectInfoService rawProjectInfoService = null;

	@Autowired
	private ProcessedProjectInfoService processedProjectInfoService = null;

	@Autowired
	private CoreUserService coreUserService = null;

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
	@RequestMapping(value = "/allprojects/{id}", method = RequestMethod.POST)
	public ResponseEntity<EntrepreneurProjectsDto> getAllProposals(@PathVariable("id") int userid) {

		try {

			EntrepreneurProjectsDto entrepreneurProjectsDto = new EntrepreneurProjectsDto();

			RawProjectInfoResponseDto rawProjectInfoResponseDto = new RawProjectInfoResponseDto();
			List<RawProjectInfoResponseDto> rawProjectInfoResponseDtoList = new ArrayList<RawProjectInfoResponseDto>();

			ProssedProjectInfoResponseDto prossedProjectInfoResponseDto = new ProssedProjectInfoResponseDto();
			List<ProssedProjectInfoResponseDto> prossedProjectInfoResponseDtoList = new ArrayList<ProssedProjectInfoResponseDto>();

			List<RawProjectInfo> rawProjectInfoList = rawProjectInfoService.findByUserId(userid);
			if (rawProjectInfoList.isEmpty()) {
				return new ResponseEntity<EntrepreneurProjectsDto>(HttpStatus.NO_CONTENT);
			}

			entrepreneurProjectsDto.setCoreUser(coreUserService.findById(userid));

			for (RawProjectInfo r : rawProjectInfoList) {
				if (r.getAdminStatus().equals(ApiConstants.ADMIN_NOT_APPROVED)) {
					rawProjectInfoResponseDto.setId(r.getId());
					rawProjectInfoResponseDto.setProjectName(r.getProjectName());
					rawProjectInfoResponseDto.setSubmitedDate(r.getSubmitedDate());
					rawProjectInfoResponseDtoList.add(rawProjectInfoResponseDto);
				} else if (r.getAdminStatus().equals(ApiConstants.ADMIN_APPROVED)) {
					ProcessedProjectInfo processedProjectInfo = processedProjectInfoService
							.findByRawProjectid(r.getId());
					prossedProjectInfoResponseDto.setId(processedProjectInfo.getId());
					prossedProjectInfoResponseDto.setProjectName(processedProjectInfo.getProjectName());
					prossedProjectInfoResponseDto.setSharePrice(processedProjectInfo.getSharePrice());
					prossedProjectInfoResponseDto.setImageUrl(processedProjectInfo.getImageUrl());
					prossedProjectInfoResponseDto.setVideoUrl(processedProjectInfo.getVideoUrl());
					prossedProjectInfoResponseDto.setFullAmmount(processedProjectInfo.getFullAmmount());
					prossedProjectInfoResponseDto.setNoOfShares(processedProjectInfo.getNoOfShares());
					prossedProjectInfoResponseDto.setMininumAmmount(processedProjectInfo.getMininumAmmount());
					prossedProjectInfoResponseDto.setType(processedProjectInfo.getType());
					prossedProjectInfoResponseDto.setCurrency(processedProjectInfo.getCurrency());
					prossedProjectInfoResponseDto.setCategory(processedProjectInfo.getCategory());
					prossedProjectInfoResponseDto.setCustomerType(processedProjectInfo.getCustomerType());
					prossedProjectInfoResponseDtoList.add(prossedProjectInfoResponseDto);
				}
			}

			entrepreneurProjectsDto.setRawProjectList(rawProjectInfoResponseDtoList);
			entrepreneurProjectsDto.setProcessedProjectList(prossedProjectInfoResponseDtoList);

			return new ResponseEntity<EntrepreneurProjectsDto>(entrepreneurProjectsDto, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<EntrepreneurProjectsDto>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}








