package com.investment.dao.core;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.investment.dao.root.RootDaoImpl;
import com.investment.entity.core.Currency;

@Repository
public class CurrencyDaoImpl extends RootDaoImpl<Currency> implements CurrencyDao{

	public Currency findByCode(String code) {
		return (Currency) getSession().createCriteria(Currency.class).add(Restrictions.like("code", code));
	}
}






