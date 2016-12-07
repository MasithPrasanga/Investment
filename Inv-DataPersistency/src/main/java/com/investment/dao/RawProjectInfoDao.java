package com.investment.dao;

import java.util.List;

import com.investment.dao.root.RootDao;
import com.investment.entity.RawProjectInfo;

public interface RawProjectInfoDao extends RootDao<RawProjectInfo>{

	public List<RawProjectInfo> findByUserId(Integer userid);
	
}



