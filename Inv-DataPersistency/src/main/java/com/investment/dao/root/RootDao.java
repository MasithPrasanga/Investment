package com.investment.dao.root;

import java.io.Serializable;
import java.util.List;

public interface RootDao<T extends Serializable> {

	public T findById(Integer id);

	public T findByEmail(String email);

	public Long persist(T message);

	public void delete(T entity);

	public void update(T entyty);

	public List<T> getAllRecords();
	
	void deleteAllRecords();
	
	
}
