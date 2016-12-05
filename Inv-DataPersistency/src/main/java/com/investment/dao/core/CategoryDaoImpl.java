package com.investment.dao.core;

import org.springframework.stereotype.Repository;

import com.investment.dao.root.RootDaoImpl;
import com.investment.entity.core.Category;

@Repository
public class CategoryDaoImpl extends RootDaoImpl<Category> implements CategoryDao{

}


