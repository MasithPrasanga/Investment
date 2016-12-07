package com.investment.dao.metadeta;

import com.investment.dao.root.RootDao;
import com.investment.entity.metadeta.Currency;

public interface CurrencyDao  extends RootDao<Currency>{

	public Currency findByCode(String code);
}
