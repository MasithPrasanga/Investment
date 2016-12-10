package com.investment.dao;

import com.investment.dao.root.RootDao;
import com.investment.entity.CoreUser;

public interface CoreUserDao  extends RootDao<CoreUser>{

	public CoreUser findByEmail(String email);
	
}
