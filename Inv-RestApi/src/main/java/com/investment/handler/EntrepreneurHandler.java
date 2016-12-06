package com.investment.handler;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investment.dto.RawProjectInfoDto;
import com.investment.entity.BusinessUpload;
import com.investment.entity.CoreUser;
import com.investment.entity.RawProjectInfo;
import com.investment.service.BusinessUploadService;
import com.investment.service.CoreUserService;
import com.investment.service.RawProjectInfoService;
import com.investment.util.ApiConstants;

@Component
public class EntrepreneurHandler {

	@Autowired
	private RawProjectInfoService rawProjectInfoService = null;

	@Autowired
	private BusinessUploadService uploadService = null;
	
	@Autowired
	private CoreUserService coreUserService = null;

	public boolean createRawProjectInfo(RawProjectInfoDto uploadedRawData) {

		try {
			List<String> urls = uploadedRawData.getUrls();
			RawProjectInfo rawProjectInfo = new RawProjectInfo();
			rawProjectInfo.setProjectName("ProjectName" + new Date());
			rawProjectInfo.setDate(new Date());
			rawProjectInfo.setAdminStatus(ApiConstants.AdminNotApproved);
			System.out.println("User Id Is : "+uploadedRawData.getUserId());
			CoreUser user = coreUserService.findById(uploadedRawData.getUserId());
			rawProjectInfo.setCoreUser(user);
			rawProjectInfoService.insert(rawProjectInfo);

			for (String url : urls) {
				BusinessUpload businessUpload = new BusinessUpload();
				businessUpload.setDate(new Date());
				businessUpload.setUrl(url);
				businessUpload.setRawData(rawProjectInfo);
				businessUpload.setCoreUser(user);
				uploadService.insert(businessUpload);

			}
			return true;

		} catch (Exception e) {
			return false;
		}

	}
}
