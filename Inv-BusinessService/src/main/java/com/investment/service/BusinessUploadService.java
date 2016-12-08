package com.investment.service;

import java.util.List;

import com.investment.entity.BusinessUpload;
import com.investment.entity.RawProjectInfo;
import com.investment.root.service.RootService;

public interface BusinessUploadService extends RootService<BusinessUpload>{
	
	public List<BusinessUpload> findListById(RawProjectInfo rawProjectInfo);
	
}
