package com.investment.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investment.dto.ProcessedProjectInfoDto;
import com.investment.entity.BusinessUpload;
import com.investment.entity.ProcessedProjectInfo;
import com.investment.entity.RawProjectInfo;
import com.investment.service.BusinessUploadService;
import com.investment.service.CoreUserService;
import com.investment.service.ProcessedProjectInfoService;
import com.investment.service.RawProjectInfoService;
import com.investment.service.metadata.CategoryService;
import com.investment.service.metadata.CurrencyService;
import com.investment.service.metadata.CustomerTypeService;
import com.investment.service.metadata.TypeService;
import com.investment.util.ApiConstants;

@Component
public class AdminHandler {

	@Autowired
	private TypeService typeService = null;

	@Autowired
	private CoreUserService coreUserService = null;

	@Autowired
	private CurrencyService currencyService = null;

	@Autowired
	private CategoryService categoryService = null;

	@Autowired
	private CustomerTypeService customerTypeService = null;

	@Autowired
	private ProcessedProjectInfoService processedProjectInfoService = null;

	@Autowired
	private RawProjectInfoService rawProjectInfoService = null;
	
	@Autowired
	private BusinessUploadService businessUploadService = null;

	@Autowired
	private SessionFactory sessionFactory;

	public boolean createProject(ProcessedProjectInfoDto processedProjectInfoDto) {

		Session session = null;
		Transaction transaction = null;
		boolean transactionStatus = false;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			ProcessedProjectInfo processedProjectInfo = new ProcessedProjectInfo();
			processedProjectInfo.setProjectName(processedProjectInfoDto.getProjectName());
			processedProjectInfo.setType(typeService.findById(processedProjectInfoDto.getTypeid()));
			processedProjectInfo.setSharePrice(processedProjectInfoDto.getSharePrice());
			processedProjectInfo.setCoreUser(coreUserService.findById(processedProjectInfoDto.getUserid()));
			processedProjectInfo.setImageUrl(processedProjectInfoDto.getImageUrl());
			processedProjectInfo.setVideoUrl(processedProjectInfoDto.getVideoUrl());
			processedProjectInfo.setFullAmmount(processedProjectInfoDto.getFullAmmount());
			processedProjectInfo.setNoOfShares(processedProjectInfoDto.getNoOfShares());
			processedProjectInfo.setMininumAmmount(processedProjectInfoDto.getMininumAmmount());
			processedProjectInfo.setCurrency(currencyService.findById(processedProjectInfoDto.getCurrencyid()));
			processedProjectInfo.setCategory(categoryService.findById(processedProjectInfoDto.getCategoryid()));
			processedProjectInfo.setCustomerType(customerTypeService.findById(processedProjectInfoDto.getCustomertypeid()));
			RawProjectInfo rawProjectInfo = rawProjectInfoService.findById(processedProjectInfoDto.getRawProjectInfoId());
			processedProjectInfo.setRawProjectInfo(rawProjectInfo);
	
			processedProjectInfoService.insert(processedProjectInfo);

			RawProjectInfo updatedProjectInfo = new RawProjectInfo();
			updatedProjectInfo.setId(rawProjectInfo.getId());
			updatedProjectInfo.setAdminStatus(ApiConstants.ADMIN_APPROVED);
			updatedProjectInfo.setSubmitedDate(rawProjectInfo.getSubmitedDate());
			updatedProjectInfo.setApprovedDate(new Date());
			updatedProjectInfo.setProjectName(rawProjectInfo.getProjectName());
			updatedProjectInfo.setCoreUser(coreUserService.findById(processedProjectInfoDto.getUserid()));
			
			rawProjectInfoService.update(updatedProjectInfo);
			
			List<BusinessUpload> businessUploadList = new ArrayList<BusinessUpload>();
			List<BusinessUpload> businessUploadListToBeUpdated = new ArrayList<BusinessUpload>();
			businessUploadList = businessUploadService.getAllRecords();
			
			for(BusinessUpload b : businessUploadList ){
				if(b.getRawData().getId() == rawProjectInfo.getId()){
					b.setProcessedProjectInfo(processedProjectInfo);
					businessUploadListToBeUpdated.add(b);
					businessUploadService.update(b);
				}
			}
				
			transaction.commit();
			transactionStatus  = true;
			return transactionStatus;

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
