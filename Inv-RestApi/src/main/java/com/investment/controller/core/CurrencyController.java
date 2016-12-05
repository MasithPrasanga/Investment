package com.investment.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.investment.dto.core.CurrencyDto;
import com.investment.entity.core.Currency;
import com.investment.handler.core.CurrencyHandler;
import com.investment.service.core.CurrencyService;

@RestController
@RequestMapping("api/v1/currency")
public class CurrencyController {

	@Autowired
	private CurrencyService currencyService = null;

	@Autowired
	private CurrencyHandler currencyHandler = null;

	// Save Raw Data (Working)
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Void> saveCurrency(@RequestBody CurrencyDto currencyDto) {
		try {
			boolean status = currencyHandler.saveCurrency(currencyDto);
			if (status) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
		}

	}
	
}
