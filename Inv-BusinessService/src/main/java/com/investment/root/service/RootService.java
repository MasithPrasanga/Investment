package com.investment.root.service;

import java.util.List;

public interface RootService<T> {

	public T findById(Integer id);

	public long insert(T entity);

	public boolean delete(T entity);

	public boolean update(T entyty);

	public List<T> getAllRecords();
	
	boolean deleteAllRecords();
	
}


