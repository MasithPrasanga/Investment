package com.investment.handler;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investment.dto.RawProjectInfoDto;
import com.investment.entity.BusinessUpload;
import com.investment.entity.RawProjectInfo;
import com.investment.service.BusinessUploadService;
import com.investment.service.RawProjectInfoService;

@Component
public class EntrepreneurHandler {

	@Autowired
	private RawProjectInfoService rawProjectInfoService = null;

	@Autowired
	private BusinessUploadService uploadService = null;

	public boolean createRawProjectInfo(RawProjectInfoDto uploadedRawData) {

		try {
			List<String> urls = uploadedRawData.getUrls();
			RawProjectInfo rawProjectInfo = new RawProjectInfo();
			rawProjectInfo.setProjectName("ProjectName" + new Date());
			rawProjectInfo.setDate(new Date());
			rawProjectInfo.setAdminStatus("Not Approved"); // add the enum and
															// create the
															// constants
			rawProjectInfoService.insert(rawProjectInfo);

			for (String url : urls) {
				BusinessUpload businessUpload = new BusinessUpload();
				businessUpload.setDate(new Date());
				businessUpload.setUrl(url);
				businessUpload.setRawData(rawProjectInfo);
				uploadService.insert(businessUpload);

			}
			return true;

		} catch (Exception e) {
			return false;
		}

	}
}
