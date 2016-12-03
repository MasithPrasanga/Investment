package com.investment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investment.dao.RawDataDao;
import com.investment.entity.RawData;

@Service
public class RawDataServiceImpl implements RawDataService {

	@Autowired
	private RawDataDao rawDataDao = null;

	public RawData findById(Integer id) {
		try {
			return rawDataDao.findById(id);
		} catch (Exception e) {
			return null;
		}
	}

	public RawData findByEmail(String email) {
		try {
			return rawDataDao.findByEmail(email);
		} catch (Exception e) {
			return null;
		}
	}

	public long insert(RawData entity) {
		try {
			return rawDataDao.persist(entity);
		} catch (Exception e) {
			return -1;
		}
	}

	public boolean delete(RawData entity) {
		try {
			rawDataDao.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update(RawData entyty) {
		try {
			rawDataDao.update(entyty);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<RawData> getAllRecords() {
		List<RawData> rawDataList = null;
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

}






