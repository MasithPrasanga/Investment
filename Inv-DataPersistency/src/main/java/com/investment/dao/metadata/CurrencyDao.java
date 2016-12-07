package com.investment.dao.metadata;

import com.investment.dao.root.RootDao;
import com.investment.entity.metadata.Currency;

public interface CurrencyDao  extends RootDao<Currency>{

	public Currency findByCode(String code);
}
