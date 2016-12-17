package com.investment.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investment.dto.request.InvestRequestDto;
import com.investment.dto.response.CoreUserResponseDto;
import com.investment.dto.response.InvestedProjectsResponseDto;
import com.investment.dto.response.ProssedProjectInfoResponseDto;
import com.investment.entity.Investment;
import com.investment.entity.ProcessedProjectInfo;
import com.investment.handler.root.RootHandler;
import com.investment.service.CoreUserService;
import com.investment.service.InvestmentService;
import com.investment.service.ProcessedProjectInfoService;
import com.investment.util.ApiConstants;

@Component
public class InvestorHandler extends RootHandler {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CoreUserService coreUserService = null;

	@Autowired
	private InvestmentService investmentService = null;

	@Autowired
	private ProcessedProjectInfoService processedProjectInfoService = null;

	public boolean makeInvestment(InvestRequestDto investmentRequest) {

		Session session = null;
		Transaction transaction = null;
		boolean transactionStatus = false;

		try {

			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			ProcessedProjectInfo processedProjectInfo = processedProjectInfoService.findById(investmentRequest.getProcessedProjectInfoId());
			
			// update processed_project_info table
			int investedFullAmount = processedProjectInfo.getInvestedAmount();
			investedFullAmount = investedFullAmount + investmentRequest.getInvestedAmount();
			if (investedFullAmount < processedProjectInfo.getFullAmmount()) {
				processedProjectInfo.setInvestedAmount(investedFullAmount);
				processedProjectInfoService.update(processedProjectInfo);
				// save data to investment table
				Investment investment = new Investment();
				investment.setInvestedDate(new Date());
				investment.setInvestedAmouont(investmentRequest.getInvestedAmount());
				investment.setProcessedProject(processedProjectInfo);
				investment.setNoOfShares(investmentRequest.getInvestedAmount() / processedProjectInfo.getSharePrice());
				investment.setInvestor(coreUserService.findById(investmentRequest.getInvestorId()));
				investment.setEntrepreneur(coreUserService.findById(investmentRequest.getEntrepreneurId()));
				double precentage = (double) investmentRequest.getInvestedAmount()/ processedProjectInfo.getFullAmmount();
				investment.setPresentageOfFullAmount(precentage * 100);
				investmentService.insert(investment);
				transactionStatus = true;
			}
			transaction.commit();
			return transactionStatus;
		} catch (Exception e) {
			transaction.rollback();
			return transactionStatus;
		} finally {
			session.flush();
			session.close();
		}
	}

	public InvestedProjectsResponseDto createInvestedProjectResponse(Investment investment) {
		InvestedProjectsResponseDto dto = new InvestedProjectsResponseDto();
		dto.setProjectName(investment.getProcessedProject().getProjectName());
		dto.setSingleSharePrice(investment.getProcessedProject().getSharePrice());
		dto.setImageUrl(investment.getProcessedProject().getImageUrl());
		dto.setVideoUrl(investment.getProcessedProject().getVideoUrl());
		dto.setFullAmmountCanInvest(investment.getProcessedProject().getFullAmmount());
		dto.setNoOfSharesCanBuy(investment.getProcessedProject().getNoOfShares());
		dto.setMininumAmmountCanInvest(investment.getProcessedProject().getMininumAmmount());
		dto.setType(investment.getProcessedProject().getType().getType());
		dto.setCurrency(investment.getProcessedProject().getCurrency().getName());
		dto.setCustomerType(investment.getProcessedProject().getCustomerType().getType());
		dto.setCategory(investment.getProcessedProject().getCategory().getName());
		dto.setInvestedDate(investment.getInvestedDate());
		dto.setInvestedAmouont(investment.getInvestedAmouont());
		dto.setInvestedNoOfShares(investment.getNoOfShares());
		dto.setInvestedPrecentage(investment.getPresentageOfFullAmount());
		return dto;
	}

	public List<ProssedProjectInfoResponseDto> getProjectsToBeInvested(int investorId) {
		
		List<Investment> investedList = investmentService.findByInvestorId(investorId);
		List<ProcessedProjectInfo> investedProcessedList = new ArrayList<ProcessedProjectInfo>();
		for(Investment inv :investedList){
			investedProcessedList.add(inv.getProcessedProject());
		}
		List<ProcessedProjectInfo> processedList = processedProjectInfoService.getAllRecords();
		List<ProcessedProjectInfo> notInvestedList = new ArrayList<ProcessedProjectInfo>();
		boolean elementInTheList = false;
		for(ProcessedProjectInfo p1 : processedList){
			for(ProcessedProjectInfo p2 : investedProcessedList){
				if(p1.getId() == p2.getId()){
					elementInTheList = true;
					break;
				}
			}
			if(!elementInTheList){
				notInvestedList.add(p1);
			}
		}
		List<ProssedProjectInfoResponseDto> responseList = new ArrayList<ProssedProjectInfoResponseDto>();
		ProssedProjectInfoResponseDto response = null;
		CoreUserResponseDto entrepreneur = null;
		for(ProcessedProjectInfo p : notInvestedList){
			if(p.getCanInvest().equals(ApiConstants.CAN_INVEST)){
				response = createProjectInfoResponse(p);	
				entrepreneur = createCoreUserResponse(p.getRawProjectInfo().getCoreUser());
				response.setEntrepreneur(entrepreneur);
				responseList.add(response);
			}
		}
		return responseList;
	}	
}


