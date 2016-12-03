package com.investment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investment.dao.UploadDao;
import com.investment.entity.Upload;

@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	private UploadDao uploadDao = null;

	public Upload findById(Integer id) {
		try {
			return uploadDao.findById(id);
		} catch (Exception e) {
			return null;
		}
	}

	public Upload findByEmail(String email) {
		try {
			return uploadDao.findByEmail(email);
		} catch (Exception e) {
			return null;
		}
	}

	public long insert(Upload entity) {
		try {
			return uploadDao.persist(entity);
		} catch (Exception e) {
			return -1;
		}
	}

	public boolean delete(Upload entity) {
		try {
			uploadDao.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update(Upload entyty) {
		try {
			uploadDao.update(entyty);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<Upload> getAllRecords() {
		List<Upload> uploadedList = null;
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

}






