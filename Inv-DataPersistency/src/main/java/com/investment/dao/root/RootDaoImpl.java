package com.investment.dao.root;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public abstract class RootDaoImpl<T extends Serializable> implements RootDao<T> {

	private final Class<T> entityClass;

	public RootDaoImpl() {
		this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return this.sessionFactory.openSession();
	}

	public T findById(Integer id) {
		return (T) getSession().get(this.entityClass, id);

	}

	public Long persist(T entity) {
		Session session = getSession();
		Transaction tx = null;
		int savedId = 0;
		try {
			tx = session.beginTransaction();
			savedId = (Integer) session.save(entity);
			tx.commit();
			return (long) savedId;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			return (long) -1;
		} finally {
			session.flush();
			session.close();
		}
	}

	public void delete(T entity) {
		Session session = getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(entity);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	public void update(T entity) {
		Session session = getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(entity);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.flush();
			session.close();
		}
	}

	public List<T> getAllRecords() {
		return getSession().createCriteria(this.entityClass).list();
	}

	public void deleteAllRecords() {
		Session session = getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<T> entities = getAllRecords();
			for (T entity : entities) {
				session.delete(entity);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

}
