package com.investment.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.investment.dao.root.RootDaoImpl;
import com.investment.entity.BusinessUpload;
import com.investment.entity.RawProjectInfo;

@Repository
public class BusinessUploadDaoImpl extends RootDaoImpl<BusinessUpload> implements BusinessUploadDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<BusinessUpload> findListById(RawProjectInfo rawProjectInfo) {
		
		Transaction transaction = null;
		Session session =  null;
		
		try{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			int id = rawProjectInfo.getId();
			String hql = "FROM BusinessUpload B WHERE B.rawData = "+id;
			Query query = session.createQuery(hql);
			List<BusinessUpload> businessUploadList = query.list();
			transaction.commit();
			return businessUploadList;
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			return null;
		}
	}

}


