package com.investment.dao;

import java.util.List;

import com.investment.dao.root.RootDao;
import com.investment.entity.BusinessUpload;
import com.investment.entity.RawProjectInfo;

public interface BusinessUploadDao  extends RootDao<BusinessUpload>{

	public List<BusinessUpload> findListById(RawProjectInfo rawProjectInfo);
}



