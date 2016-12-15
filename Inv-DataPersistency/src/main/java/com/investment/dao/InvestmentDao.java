package com.investment.dao;

import java.util.List;

import com.investment.dao.root.RootDao;
import com.investment.entity.Investment;

public interface InvestmentDao  extends RootDao<Investment>{

	public List<Investment> findByInvestorId(Integer investorId);
}
