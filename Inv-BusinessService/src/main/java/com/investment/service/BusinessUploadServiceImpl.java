package com.investment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investment.dao.BusinessUploadDao;
import com.investment.entity.BusinessUpload;
import com.investment.entity.RawProjectInfo;
import com.investment.util.ApiConstants;

@Service
public class BusinessUploadServiceImpl implements BusinessUploadService {

	@Autowired
	private BusinessUploadDao uploadDao = null;

	public BusinessUpload findById(Integer id) {
		try {
			return uploadDao.findById(id);
		} catch (Exception e) {
			return null;
		}
	}

	public long insert(BusinessUpload entity) {
		try {
			return uploadDao.persist(entity);
		} catch (Exception e) {
			return ApiConstants.PERSISTED_EXCEPTION;
		}
	}

	public boolean delete(BusinessUpload entity) {
		try {
			uploadDao.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update(BusinessUpload entyty) {
		try {
			uploadDao.update(entyty);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<BusinessUpload> getAllRecords() {
		List<BusinessUpload> uploadedList = null;
		try {
			uploadedList = uploadDao.getAllRecords();
		} catch (Exception e) {
			return uploadedList;
		}
		return uploadedList;
	}

	public boolean deleteAllRecords() {
		try {
			uploadDao.deleteAllRecords();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<BusinessUpload> findListById(RawProjectInfo rawProjectInfo) {
		try {
			return (List<BusinessUpload>)uploadDao.findListById(rawProjectInfo);
		} catch (Exception e) {
			return null;
		}
	}
	
}









