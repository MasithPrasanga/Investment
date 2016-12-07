package com.investment.service.metadeta;

import com.investment.entity.metadeta.Currency;
import com.investment.root.service.RootService;

public interface CurrencyService extends RootService<Currency>{

	public Currency findByCode(String code);
}
