package com.investment.dao;

import org.springframework.stereotype.Repository;

import com.investment.dao.root.RootDaoImpl;
import com.investment.entity.Investment;

@Repository
public class InvestmentDaoImpl extends RootDaoImpl<Investment> implements InvestmentDao{

}


