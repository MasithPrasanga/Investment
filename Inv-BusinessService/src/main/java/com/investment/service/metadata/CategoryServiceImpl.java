package com.investment.service.metadata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investment.dao.metadata.CategoryDao;
import com.investment.entity.metadata.Category;
import com.investment.util.ApiConstants;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao = null;

	public Category findById(Integer id) {
		try {
			return categoryDao.findById(id);
		} catch (Exception e) {
			return null;
		}
	}

	public long insert(Category entity) {
		try {
			return categoryDao.persist(entity);
		} catch (Exception e) {
			return ApiConstants.PERSISTED_EXCEPTION;
		}
	}

	public boolean delete(Category entity) {
		try {
			categoryDao.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update(Category entyty) {
		try {
			categoryDao.update(entyty);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Category> getAllRecords() {
		List<Category> uploadedList = null;
		try {
			uploadedList = categoryDao.getAllRecords();
		} catch (Exception e) {
			return uploadedList;
		}
		return uploadedList;
	}

	public boolean deleteAllRecords() {
		try {
			categoryDao.deleteAllRecords();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}




