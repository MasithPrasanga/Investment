package com.investment.service.metadata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investment.dao.metadata.CurrencyDao;
import com.investment.entity.metadata.Currency;
import com.investment.util.ApiConstants;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private CurrencyDao currencyDao = null;

	public Currency findById(Integer id) {
		try {
			return currencyDao.findById(id);
		} catch (Exception e) {
			return null;
		}
	}

	public long insert(Currency entity) {
		try {
			return currencyDao.persist(entity);
		} catch (Exception e) {
			return ApiConstants.PERSISTED_EXCEPTION;
		}
	}

	public boolean delete(Currency entity) {
		try {
			currencyDao.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update(Currency entyty) {
		try {
			currencyDao.update(entyty);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Currency> getAllRecords() {
		List<Currency> uploadedList = null;
		try {
			uploadedList = currencyDao.getAllRecords();
		} catch (Exception e) {
			return uploadedList;
		}
		return uploadedList;
	}

	public boolean deleteAllRecords() {
		try {
			currencyDao.deleteAllRecords();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Currency findByCode(String code) {
		try {
			return currencyDao.findByCode(code);
		} catch (Exception e) {
			return null;
		}
	}

}
