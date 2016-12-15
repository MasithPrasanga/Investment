package com.investment.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.investment.dao.root.RootDaoImpl;
import com.investment.entity.Investment;

@Repository
public class InvestmentDaoImpl extends RootDaoImpl<Investment> implements InvestmentDao{

	@Autowired
	private SessionFactory sessionFactory;
	public List<Investment> findByInvestorId(Integer investorId) {
		Transaction transaction = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			String hql = "FROM Investment I WHERE I.investor = " + investorId;
			Query query = session.createQuery(hql);
			List<Investment> investedProjectList = query.list();
			transaction.commit();
			return investedProjectList;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return null;
		}
	}
}






