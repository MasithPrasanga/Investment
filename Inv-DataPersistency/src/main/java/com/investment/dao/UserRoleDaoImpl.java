package com.investment.dao;

import org.springframework.stereotype.Repository;

import com.investment.dao.root.RootDaoImpl;
import com.investment.entity.CoreUser;

@Repository
public class UserRoleDaoImpl extends RootDaoImpl<CoreUser> implements UserRoleDao{

}


