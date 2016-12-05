package com.investment.handler.core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investment.dto.core.CurrencyDto;
import com.investment.entity.core.Currency;
import com.investment.service.core.CurrencyService;

@Component
public class CurrencyHandler {

	@Autowired
	private CurrencyService currencyService = null;
	
	public boolean saveCurrency(CurrencyDto currencyDto){
		try{
			Currency currency = new Currency();
			currency.setCode(currencyDto.getCode());
			currency.setName(currencyDto.getName());
			long status = currencyService.insert(currency);
			System.out.println("Currency Handler Status : "+status);
			if(status == -1){
				return false;
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
}
