package com.investment.dao;

import org.springframework.stereotype.Repository;

import com.investment.dao.root.RootDaoImpl;
import com.investment.entity.RawData;

@Repository
public class RawDataDaoImpl extends RootDaoImpl<RawData> implements RawDataDao{

}


