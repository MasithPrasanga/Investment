package com.investment.dao.metadata;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.investment.dao.root.RootDaoImpl;
import com.investment.entity.metadata.Currency;

@Repository
public class CurrencyDaoImpl extends RootDaoImpl<Currency> implements CurrencyDao{

	public Currency findByCode(String code) {
		return (Currency) getSession().createCriteria(Currency.class).add(Restrictions.like("code", code));
	}
}






