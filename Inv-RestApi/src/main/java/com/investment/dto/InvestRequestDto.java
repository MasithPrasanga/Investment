package com.investment.dto;

import java.io.Serializable;

public class InvestRequestDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int investedAmount;
	
	private int investorId;
	
	private int entrepreneurId;
	
	private int processedProjectInfoId;

	public int getInvestedAmount() {
		return investedAmount;
	}

	public void setInvestedAmount(int investedAmount) {
		this.investedAmount = investedAmount;
	}

	public int getInvestorId() {
		return investorId;
	}

	public void setInvestorId(int investorId) {
		this.investorId = investorId;
	}

	public int getEntrepreneurId() {
		return entrepreneurId;
	}

	public void setEntrepreneurId(int entrepreneurId) {
		this.entrepreneurId = entrepreneurId;
	}

	public int getProcessedProjectInfoId() {
		return processedProjectInfoId;
	}

	public void setProcessedProjectInfoId(int processedProjectInfoId) {
		this.processedProjectInfoId = processedProjectInfoId;
	}
	
}


