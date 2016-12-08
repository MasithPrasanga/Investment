package com.investment.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investment.dto.RawProjectInfoDto;
import com.investment.entity.BusinessUpload;
import com.investment.entity.CoreUser;
import com.investment.entity.RawProjectInfo;
import com.investment.service.CoreUserService;
import com.investment.service.RawProjectInfoService;
import com.investment.util.ApiConstants;

@Component
public class EntrepreneurHandler {

	@Autowired
	private RawProjectInfoService rawProjectInfoService = null;

	@Autowired
	private CoreUserService coreUserService = null;

	@Autowired
	private SessionFactory sessionFactory;

	public boolean createRawProjectInfo(RawProjectInfoDto uploadedRawData) {

		Session session = null;
		Transaction transaction = null;
		boolean transactionStatus = false;

		try {
			
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			List<String> urls = uploadedRawData.getUrls();
			RawProjectInfo rawProjectInfo = new RawProjectInfo();
			CoreUser user = coreUserService.findById(uploadedRawData.getUserId());
			rawProjectInfo.setCoreUser(user);
			rawProjectInfo.setProjectName(user.getId() + " ProjectName " + new Date());
			rawProjectInfo.setSubmitedDate(new Date());
			rawProjectInfo.setAdminStatus(ApiConstants.ADMIN_NOT_APPROVED);
			List<BusinessUpload> businessUploadList = new ArrayList<BusinessUpload>();
			for (String url : urls) {
				BusinessUpload businessUpload = new BusinessUpload();
				businessUpload.setDate(new Date());
				businessUpload.setUrl(url);
				businessUpload.setRawData(rawProjectInfo);
				businessUploadList.add(businessUpload);
			}
			rawProjectInfo.setBusinessUpload(businessUploadList);
			
			int saveedId = (int) rawProjectInfoService.insert(rawProjectInfo);
			
			transaction.commit();
			
			if (saveedId != ApiConstants.PERSISTED_EXCEPTION){
				transactionStatus = true;
			}
			
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		
		return transactionStatus;

	}
}









