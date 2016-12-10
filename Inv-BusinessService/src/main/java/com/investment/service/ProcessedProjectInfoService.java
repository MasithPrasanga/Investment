package com.investment.service;

import com.investment.entity.ProcessedProjectInfo;
import com.investment.root.service.RootService;

public interface ProcessedProjectInfoService extends RootService<ProcessedProjectInfo>{

	public ProcessedProjectInfo findByRawProjectid(Integer rawProjectId);
}
