package com.investment.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.investment.dao.root.RootDaoImpl;
import com.investment.entity.CoreUser;

@Repository
public class CoreUserDaoImpl extends RootDaoImpl<CoreUser> implements CoreUserDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	Transaction transaction = null;
	Session session = null;
	
	public CoreUser findByEmail(String email) {
	
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			String hql = "FROM CoreUser C WHERE C.userEmail = "+"'"+email+"'";
			Query query = session.createQuery(hql);
			CoreUser coreuser = (CoreUser) query.uniqueResult();
			transaction.commit();
			return coreuser;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return null;
		}finally{
			session.close();
		}
		
	}

	public CoreUser findByActivationCode(String activationCode) {
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			String hql = "FROM CoreUser C WHERE C.activationCode = "+"'"+activationCode+"'";
			Query query = session.createQuery(hql);
			CoreUser coreuser = (CoreUser) query.uniqueResult();
			transaction.commit();
			return coreuser;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return null;
		}finally{
			session.close();
		}
	}
	
}










