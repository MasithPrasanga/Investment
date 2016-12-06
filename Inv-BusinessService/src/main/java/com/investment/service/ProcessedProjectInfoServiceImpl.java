package com.investment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investment.dao.ProcessedProjectInfoDao;
import com.investment.entity.ProcessedProjectInfo;

@Service
public class ProcessedProjectInfoServiceImpl implements ProcessedProjectInfoService {

	@Autowired
	private ProcessedProjectInfoDao processedProjectInfoDao = null;

	public ProcessedProjectInfo findById(Integer id) {
		try {
			return processedProjectInfoDao.findById(id);
		} catch (Exception e) {
			return null;
		}
	}

	public long insert(ProcessedProjectInfo entity) {
		try {
			return processedProjectInfoDao.persist(entity);
		} catch (Exception e) {
			return -1;
		}
	}

	public boolean delete(ProcessedProjectInfo entity) {
		try {
			processedProjectInfoDao.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update(ProcessedProjectInfo entyty) {
		try {
			processedProjectInfoDao.update(entyty);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<ProcessedProjectInfo> getAllRecords() {
		List<ProcessedProjectInfo> rawDataList = null;
		try {
			rawDataList = processedProjectInfoDao.getAllRecords();
		} catch (Exception e) {
			return rawDataList;
		}
		return rawDataList;
	}

	public boolean deleteAllRecords() {
		try {
			processedProjectInfoDao.deleteAllRecords();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}






