package com.investment.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.investment.dao.root.RootDaoImpl;
import com.investment.entity.ProcessedProjectInfo;

@Repository
public class ProcessedProjectInfoDaoImpl extends RootDaoImpl<ProcessedProjectInfo> implements ProcessedProjectInfoDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public ProcessedProjectInfo findByRawProjectid(Integer rawProjectId) {
		Transaction transaction = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			String hql = "FROM ProcessedProjectInfo P WHERE P.rawProjectInfo = " + rawProjectId;
			Query query = session.createQuery(hql);
			ProcessedProjectInfo processedProjectInfoList = (ProcessedProjectInfo) query.uniqueResult();
			transaction.commit();
			return processedProjectInfoList;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return null;
		}
	}

	
}


