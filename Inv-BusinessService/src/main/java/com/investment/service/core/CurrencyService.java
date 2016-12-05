package com.investment.service.core;

import com.investment.entity.core.Currency;
import com.investment.root.service.RootService;

public interface CurrencyService extends RootService<Currency>{

	public Currency findByCode(String code);
}
