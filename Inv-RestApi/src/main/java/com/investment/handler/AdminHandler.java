package com.investment.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investment.dto.ProcessedProjectInfoDto;
import com.investment.entity.ProcessedProjectInfo;
import com.investment.entity.RawProjectInfo;
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
	
	public boolean createProject(ProcessedProjectInfoDto processedProjectInfoDto){
		
		try{
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
			processedProjectInfoService.insert(processedProjectInfo);
			
			RawProjectInfo rawProjectInfo = rawProjectInfoService.findById(processedProjectInfoDto.getRawProjectInfoId());
			if (rawProjectInfo == null) {
				return false;
			}
			RawProjectInfo updatedProjectInfo = new RawProjectInfo();
			updatedProjectInfo.setId(rawProjectInfo.getId());;
			updatedProjectInfo.setAdminStatus(ApiConstants.ADMIN_APPROVED);
			updatedProjectInfo.setDate(rawProjectInfo.getDate());
			updatedProjectInfo.setProjectName(rawProjectInfo.getProjectName());
			updatedProjectInfo.setCoreUser(coreUserService.findById(processedProjectInfoDto.getUserid()));
			System.out.println("RawProjectInfo Object : "+updatedProjectInfo);
			rawProjectInfoService.update(updatedProjectInfo);
			
			return true;
			
		}catch(Exception e){
			return false;
		}
	}

}

















