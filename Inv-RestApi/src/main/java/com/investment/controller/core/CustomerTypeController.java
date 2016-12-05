package com.investment.controller.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.investment.dto.core.CustomerTypeDto;
import com.investment.entity.core.CustomerType;
import com.investment.handler.core.CustomerTypeHandler;
import com.investment.service.core.CustomerTypeService;

@RestController
@RequestMapping("api/v1/customertype")
public class CustomerTypeController {

	@Autowired
	private CustomerTypeService customerTypeService = null;

	@Autowired
	private CustomerTypeHandler customerTypeHandler = null;

	// findById
	@RequestMapping(value = "/findbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<CustomerType> customerTypeFindById(@PathVariable("id") int id) {
		CustomerType CustomerType = customerTypeService.findById(id);
		if (CustomerType == null) {
			return new ResponseEntity<CustomerType>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CustomerType>(CustomerType, HttpStatus.OK);
	}

	// save currency
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Void> saveCustomerType(@RequestBody CustomerTypeDto customerTypeDto) {
		try {
			boolean status = customerTypeHandler.saveCustomerType(customerTypeDto);
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
	public ResponseEntity<CustomerType> updateCurrency(@PathVariable("id") int id, @RequestBody CustomerTypeDto customerTypeDto) {
		CustomerType customerType = customerTypeService.findById(id);
		if (customerType == null) {
			return new ResponseEntity<CustomerType>(HttpStatus.NOT_FOUND);
		}
		boolean status = customerTypeHandler.updateCustomerType(id, customerTypeDto);
		if (status) {
			return new ResponseEntity<CustomerType>(customerTypeService.findById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomerType>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	// Delete Currency
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CustomerType> deleteCurrency(@PathVariable("id") int id) {
		CustomerType customerType = customerTypeService.findById(id);
		if (customerType == null) {
			return new ResponseEntity<CustomerType>(HttpStatus.NOT_FOUND);
		}
		boolean status = customerTypeService.delete(customerType);
		if (status) {
			return new ResponseEntity<CustomerType>(HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomerType>(HttpStatus.EXPECTATION_FAILED);
		}

	}

	// Delete All Records
	@RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
	public ResponseEntity<CustomerType> deleteAllCurrency() {
		boolean status = customerTypeService.deleteAllRecords();
		if (status) {
			return new ResponseEntity<CustomerType>(HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomerType>(HttpStatus.NO_CONTENT);
		}

	}

	// Get All Records
	@RequestMapping(value = "/allrecords", method = RequestMethod.GET)
	public ResponseEntity<List<CustomerType>> getAllCurrency() {
		List<CustomerType> currencyList = customerTypeService.getAllRecords();
		if (currencyList.isEmpty()) {
			return new ResponseEntity<List<CustomerType>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CustomerType>>(currencyList, HttpStatus.OK);
	}

}














