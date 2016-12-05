package com.investment.dao.core;

import org.springframework.stereotype.Repository;

import com.investment.dao.root.RootDaoImpl;
import com.investment.entity.core.Currency;

@Repository
public class CurrencyDaoImpl extends RootDaoImpl<Currency> implements CurrencyDao{

}


