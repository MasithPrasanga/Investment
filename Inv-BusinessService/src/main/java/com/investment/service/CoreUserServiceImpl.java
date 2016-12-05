package com.investment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investment.dao.CoreUserDao;
import com.investment.entity.CoreUser;

@Service
public class CoreUserServiceImpl implements CoreUserService {

	@Autowired
	private CoreUserDao coreUserDao = null;

	public CoreUser findById(Integer id) {
		try {
			return coreUserDao.findById(id);
		} catch (Exception e) {
			return null;
		}
	}

	public CoreUser findByEmail(String email) {
		try {
			return coreUserDao.findByEmail(email);
		} catch (Exception e) {
			return null;
		}
	}

	public long insert(CoreUser entity) {
		try {
			return coreUserDao.persist(entity);
		} catch (Exception e) {
			return -1;
		}
	}

	public boolean delete(CoreUser entity) {
		try {
			coreUserDao.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update(CoreUser entyty) {
		try {
			coreUserDao.update(entyty);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<CoreUser> getAllRecords() {
		List<CoreUser> uploadedList = null;
		try {
			uploadedList = coreUserDao.getAllRecords();
		} catch (Exception e) {
			return uploadedList;
		}
		return uploadedList;
	}

	public boolean deleteAllRecords() {
		try {
			coreUserDao.deleteAllRecords();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
