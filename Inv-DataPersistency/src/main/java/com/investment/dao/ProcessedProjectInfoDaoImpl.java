package com.investment.dao;

import org.springframework.stereotype.Repository;

import com.investment.dao.root.RootDaoImpl;
import com.investment.entity.ProcessedProjectInfo;

@Repository
public class ProcessedProjectInfoDaoImpl extends RootDaoImpl<ProcessedProjectInfo> implements ProcessedProjectInfoDao{

}


