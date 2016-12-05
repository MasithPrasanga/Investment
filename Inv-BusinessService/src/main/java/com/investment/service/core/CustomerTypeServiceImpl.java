package com.investment.service.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investment.dao.core.CustomerTypeDao;
import com.investment.entity.core.CustomerType;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {

	@Autowired
	private CustomerTypeDao customerTypeDao = null;

	public CustomerType findById(Integer id) {
		try {
			return customerTypeDao.findById(id);
		} catch (Exception e) {
			return null;
		}
	}

	public long insert(CustomerType entity) {
		try {
			return customerTypeDao.persist(entity);
		} catch (Exception e) {
			return -1;
		}
	}

	public boolean delete(CustomerType entity) {
		try {
			customerTypeDao.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update(CustomerType entyty) {
		try {
			customerTypeDao.update(entyty);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<CustomerType> getAllRecords() {
		List<CustomerType> uploadedList = null;
		try {
			uploadedList = customerTypeDao.getAllRecords();
		} catch (Exception e) {
			return uploadedList;
		}
		return uploadedList;
	}

	public boolean deleteAllRecords() {
		try {
			customerTypeDao.deleteAllRecords();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}




