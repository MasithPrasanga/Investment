package com.investment.dao.metadata;

import org.springframework.stereotype.Repository;

import com.investment.dao.root.RootDaoImpl;
import com.investment.entity.metadata.CustomerType;

@Repository
public class CustomerTypeDaoImpl extends RootDaoImpl<CustomerType> implements CustomerTypeDao{

}


