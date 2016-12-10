package com.investment.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.investment.dao.root.RootDaoImpl;
import com.investment.entity.RawProjectInfo;

@Repository
@SuppressWarnings("unchecked")
public class RawProjectInfoDaoImpl extends RootDaoImpl<RawProjectInfo> implements RawProjectInfoDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<RawProjectInfo> findByUserId(Integer userid) {
		Transaction transaction = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			String hql = "FROM RawProjectInfo R WHERE R.coreUser = " + userid;
			Query query = session.createQuery(hql);
			List<RawProjectInfo> rawProjectInfoList = query.list();
			transaction.commit();
			return rawProjectInfoList;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return null;
		}
	}
}

















