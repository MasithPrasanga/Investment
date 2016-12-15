package com.investment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investment.dao.InvestmentDao;
import com.investment.entity.Investment;
import com.investment.util.ApiConstants;

@Service
public class InvestmentServiceImpl implements InvestmentService {

	@Autowired
	private InvestmentDao investmentDao = null;

	public Investment findById(Integer id) {
		try {
			return investmentDao.findById(id);
		} catch (Exception e) {
			return null;
		}
	}

	public long insert(Investment entity) {
		try {
			return investmentDao.persist(entity);
		} catch (Exception e) {
			return ApiConstants.PERSISTED_EXCEPTION;
		}
	}

	public boolean delete(Investment entity) {
		try {
			investmentDao.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update(Investment entyty) {
		try {
			investmentDao.update(entyty);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Investment> getAllRecords() {
		List<Investment> investmentList = null;
		try {
			investmentList = investmentDao.getAllRecords();
		} catch (Exception e) {
			return investmentList;
		}
		return investmentList;
	}

	public boolean deleteAllRecords() {
		try {
			investmentDao.deleteAllRecords();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Investment> findByInvestorId(Integer investorId) {
		try {
			return investmentDao.findByInvestorId(investorId);
		} catch (Exception e) {
			return null;
		}
	}

}
