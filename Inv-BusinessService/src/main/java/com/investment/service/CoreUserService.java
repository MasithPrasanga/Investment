package com.investment.service;

import com.investment.entity.CoreUser;
import com.investment.root.service.RootService;

public interface CoreUserService extends RootService<CoreUser>{

	public CoreUser findByEmail(String email);
	
	public CoreUser findByActivationCode(String email);
}
