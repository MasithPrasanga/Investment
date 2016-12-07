package com.investment.dao.metadata;

import org.springframework.stereotype.Repository;

import com.investment.dao.root.RootDaoImpl;
import com.investment.entity.metadata.Category;

@Repository
public class CategoryDaoImpl extends RootDaoImpl<Category> implements CategoryDao{

}


