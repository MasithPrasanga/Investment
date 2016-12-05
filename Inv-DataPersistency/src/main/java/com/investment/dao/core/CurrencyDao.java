package com.investment.dao.core;

import com.investment.dao.root.RootDao;
import com.investment.entity.core.Currency;

public interface CurrencyDao  extends RootDao<Currency>{

	public Currency findByCode(String code);
}
