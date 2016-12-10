package com.investment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investment.dao.RawProjectInfoDao;
import com.investment.entity.RawProjectInfo;
import com.investment.util.ApiConstants;

@Service
public class RawProjectInfoServiceImpl implements RawProjectInfoService {

	@Autowired
	private RawProjectInfoDao rawDataDao = null;

	public RawProjectInfo findById(Integer id) {
		try {
			return rawDataDao.findById(id);
		} catch (Exception e) {
			return null;
		}
	}

	public long insert(RawProjectInfo entity) {
		try {
			return rawDataDao.persist(entity);
		} catch (Exception e) {
			return ApiConstants.PERSISTED_EXCEPTION;
		}
	}

	public boolean delete(RawProjectInfo entity) {
		try {
			rawDataDao.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update(RawProjectInfo entyty) {
		try {
			rawDataDao.update(entyty);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<RawProjectInfo> getAllRecords() {
		List<RawProjectInfo> rawDataList = null;
		try {
			rawDataList = rawDataDao.getAllRecords();
		} catch (Exception e) {
			return rawDataList;
		}
		return rawDataList;
	}

	public boolean deleteAllRecords() {
		try {
			rawDataDao.deleteAllRecords();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<RawProjectInfo> findByUserId(Integer userid) {
		try {
			return (List<RawProjectInfo>) rawDataDao.findByUserId(userid);
		} catch (Exception e) {
			return null;
		}
	}

}






