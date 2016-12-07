package com.investment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investment.dao.UserRoleDao;
import com.investment.entity.UserRole;
import com.investment.util.ApiConstants;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao = null;

	public UserRole findById(Integer id) {
		try {
			return userRoleDao.findById(id);
		} catch (Exception e) {
			return null;
		}
	}

	public long insert(UserRole entity) {
		try {
			return userRoleDao.persist(entity);
		} catch (Exception e) {
			return ApiConstants.PERSISTED_EXCEPTION;
		}
	}

	public boolean delete(UserRole entity) {
		try {
			userRoleDao.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update(UserRole entyty) {
		try {
			userRoleDao.update(entyty);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<UserRole> getAllRecords() {
		List<UserRole> rawDataList = null;
		try {
			rawDataList = userRoleDao.getAllRecords();
		} catch (Exception e) {
			return rawDataList;
		}
		return rawDataList;
	}

	public boolean deleteAllRecords() {
		try {
			userRoleDao.deleteAllRecords();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}






