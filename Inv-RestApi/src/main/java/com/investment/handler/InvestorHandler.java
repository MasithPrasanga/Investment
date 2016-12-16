package com.investment.handler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investment.dto.request.InvestRequestDto;
import com.investment.entity.Investment;
import com.investment.entity.ProcessedProjectInfo;
import com.investment.handler.root.RootHandler;
import com.investment.service.CoreUserService;
import com.investment.service.ProcessedProjectInfoService;

@Component
public class InvestorHandler extends RootHandler{
	
	@Autowired
	private CoreUserService coreUserService = null;
	
	@Autowired
	private ProcessedProjectInfoService processedProjectInfoService = null;

	public Investment makeInvestment(InvestRequestDto investmentRequest){
		Investment investment = new Investment();
		investment.setInvestedDate(new Date());
		investment.setInvestedAmouont(investmentRequest.getInvestedAmount());
		ProcessedProjectInfo processedProjectInfo = processedProjectInfoService.findById(investmentRequest.getProcessedProjectInfoId());
		investment.setProcessedProject(processedProjectInfo);
		investment.setNoOfShares(investmentRequest.getInvestedAmount()/processedProjectInfo.getSharePrice());
		investment.setInvestor(coreUserService.findById(investmentRequest.getInvestorId()));
		investment.setEntrepreneur(coreUserService.findById(investmentRequest.getEntrepreneurId()));
		double precentage =(double)investmentRequest.getInvestedAmount()/processedProjectInfo.getFullAmmount();
		investment.setPresentageOfFullAmount(precentage*100);
		return investment;
	}

}

















