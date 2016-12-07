package com.investment.service.metadeta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investment.dao.metadeta.TypeDao;
import com.investment.entity.metadeta.Type;

@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeDao typeDao = null;

	public Type findById(Integer id) {
		try {
			return typeDao.findById(id);
		} catch (Exception e) {
			return null;
		}
	}

	public long insert(Type entity) {
		try {
			return typeDao.persist(entity);
		} catch (Exception e) {
			return -1;
		}
	}

	public boolean delete(Type entity) {
		try {
			typeDao.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update(Type entyty) {
		try {
			typeDao.update(entyty);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Type> getAllRecords() {
		List<Type> uploadedList = null;
		try {
			uploadedList = typeDao.getAllRecords();
		} catch (Exception e) {
			return uploadedList;
		}
		return uploadedList;
	}

	public boolean deleteAllRecords() {
		try {
			typeDao.deleteAllRecords();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}




