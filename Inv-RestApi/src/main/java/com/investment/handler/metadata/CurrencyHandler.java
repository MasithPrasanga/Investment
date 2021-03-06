package com.investment.handler.metadata;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investment.dto.metadata.CurrencyDto;
import com.investment.entity.metadata.Currency;
import com.investment.service.metadata.CurrencyService;

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
			if(status == -1){
				return false;
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean updateCurrency(int id,CurrencyDto currencyDto){
		boolean status = false;
		try{
			Currency currency = new Currency();
			currency.setId(id);
			currency.setCode(currencyDto.getCode());
			currency.setName(currencyDto.getName());
			status =  currencyService.update(currency);
			return status;
		}catch(Exception e){
			return status;
		}
	}
	
}














