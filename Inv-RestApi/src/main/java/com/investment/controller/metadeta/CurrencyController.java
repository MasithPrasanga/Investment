package com.investment.controller.metadeta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.investment.dto.metadeta.CurrencyDto;
import com.investment.entity.metadeta.Currency;
import com.investment.handler.metadeta.CurrencyHandler;
import com.investment.service.metadeta.CurrencyService;

@RestController
@RequestMapping("api/v1/currency")
public class CurrencyController {

	@Autowired
	private CurrencyService currencyService = null;

	@Autowired
	private CurrencyHandler currencyHandler = null;

	/*// findByCode (not Working)
	@RequestMapping(value = "/findbycode", method = RequestMethod.POST)
	public ResponseEntity<Currency> currencyFindByCode(@RequestBody CurrencyDto currencyDto) {
		Currency currency = currencyService.findByCode(currencyDto.getCode());
		System.out.println("Currency Object " + currency);
		if (currency == null) {
			return new ResponseEntity<Currency>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Currency>(currency, HttpStatus.OK);
	}
*/
	// findById
	@RequestMapping(value = "/findbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<Currency> currencyFindById(@PathVariable("id") int id) {
		Currency currency = currencyService.findById(id);
		if (currency == null) {
			return new ResponseEntity<Currency>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Currency>(currency, HttpStatus.OK);
	}

	// save currency
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

	// Update RawData (Working)
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Currency> updateCurrency(@PathVariable("id") int id, @RequestBody CurrencyDto currencyDto) {
		Currency currency = currencyService.findById(id);
		if (currency == null) {
			return new ResponseEntity<Currency>(HttpStatus.NOT_FOUND);
		}
		boolean status = currencyHandler.updateCurrency(id, currencyDto);
		if (status) {
			return new ResponseEntity<Currency>(currencyService.findById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<Currency>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	// Delete Currency
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Currency> deleteCurrency(@PathVariable("id") int id) {
		Currency currency = currencyService.findById(id);
		if (currency == null) {
			return new ResponseEntity<Currency>(HttpStatus.NOT_FOUND);
		}
		boolean status = currencyService.delete(currency);
		if (status) {
			return new ResponseEntity<Currency>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Currency>(HttpStatus.EXPECTATION_FAILED);
		}

	}

	// Delete All Records
	@RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
	public ResponseEntity<Currency> deleteAllCurrency() {
		boolean status = currencyService.deleteAllRecords();
		if (status) {
			return new ResponseEntity<Currency>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Currency>(HttpStatus.NO_CONTENT);
		}

	}

	// Get All Records
	@RequestMapping(value = "/allrecords", method = RequestMethod.GET)
	public ResponseEntity<List<Currency>> getAllCurrency() {
		List<Currency> currencyList = currencyService.getAllRecords();
		if (currencyList.isEmpty()) {
			return new ResponseEntity<List<Currency>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Currency>>(currencyList, HttpStatus.OK);
	}

}












