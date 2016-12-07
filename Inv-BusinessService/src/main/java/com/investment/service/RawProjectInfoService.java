package com.investment.service;

import java.util.List;

import com.investment.entity.RawProjectInfo;
import com.investment.root.service.RootService;

public interface RawProjectInfoService extends RootService<RawProjectInfo>{
	
	public List<RawProjectInfo> findByUserId(Integer userid);
	
}
