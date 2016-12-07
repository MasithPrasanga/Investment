package com.investment.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.investment.dao.root.RootDaoImpl;
import com.investment.entity.RawProjectInfo;

@Repository
@SuppressWarnings("unchecked")
public class RawProjectInfoDaoImpl extends RootDaoImpl<RawProjectInfo> implements RawProjectInfoDao{

	public List<RawProjectInfo> findByUserId(Integer userid) {
		try{
			return getSession().createCriteria(RawProjectInfo.class).add(Restrictions.eq("coreUser", userid)).list();
		}
		catch(Exception e){
			System.out.println("Dao Layer Exception : "+e);
			return null;
		}
		
	}

}


