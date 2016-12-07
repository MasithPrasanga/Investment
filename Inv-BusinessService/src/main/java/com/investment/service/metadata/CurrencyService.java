package com.investment.service.metadata;

import com.investment.entity.metadata.Currency;
import com.investment.root.service.RootService;

public interface CurrencyService extends RootService<Currency>{

	public Currency findByCode(String code);
}
