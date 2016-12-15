package com.investment.service;

import java.util.List;

import com.investment.entity.Investment;
import com.investment.root.service.RootService;

public interface InvestmentService extends RootService<Investment>{

	public List<Investment> findByInvestorId(Integer investorId);
}
