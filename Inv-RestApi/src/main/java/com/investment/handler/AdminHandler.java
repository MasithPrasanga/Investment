package com.investment.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investment.dto.ProcessedProjectInfoDto;
import com.investment.entity.ProcessedProjectInfo;
import com.investment.service.CoreUserService;
import com.investment.service.ProcessedProjectInfoService;
import com.investment.service.core.CategoryService;
import com.investment.service.core.CurrencyService;
import com.investment.service.core.CustomerTypeService;
import com.investment.service.core.TypeService;

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
			
			
			return true;
		}catch(Exception e){
			return false;
		}
	}

}

















