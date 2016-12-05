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

import com.investment.dto.core.TypeDto;
import com.investment.entity.core.Type;
import com.investment.handler.core.TypeHandler;
import com.investment.service.core.TypeService;

@RestController
@RequestMapping("api/v1/type")
public class TypeController {

	@Autowired
	private TypeService typeService = null;

	@Autowired
	private TypeHandler typeHandler = null;

	// findById
	@RequestMapping(value = "/findbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<Type> typeFindById(@PathVariable("id") int id) {
		Type type = typeService.findById(id);
		if (type == null) {
			return new ResponseEntity<Type>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Type>(type, HttpStatus.OK);
	}

	// save currency
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Void> saveType(@RequestBody TypeDto typeDto) {
		try {
			boolean status = typeHandler.saveType(typeDto);
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
	public ResponseEntity<Type> updateType(@PathVariable("id") int id, @RequestBody TypeDto typeDto) {
		Type type = typeService.findById(id);
		if (type == null) {
			return new ResponseEntity<Type>(HttpStatus.NOT_FOUND);
		}
		boolean status = typeHandler.updateType(id, typeDto);
		if (status) {
			return new ResponseEntity<Type>(typeService.findById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<Type>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	// Delete Currency
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Type> deleteType(@PathVariable("id") int id) {
		Type type = typeService.findById(id);
		if (type == null) {
			return new ResponseEntity<Type>(HttpStatus.NOT_FOUND);
		}
		boolean status = typeService.delete(type);
		if (status) {
			return new ResponseEntity<Type>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Type>(HttpStatus.EXPECTATION_FAILED);
		}

	}

	// Delete All Records
	@RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
	public ResponseEntity<Type> deleteAllType() {
		boolean status = typeService.deleteAllRecords();
		if (status) {
			return new ResponseEntity<Type>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Type>(HttpStatus.NO_CONTENT);
		}

	}

	// Get All Records
	@RequestMapping(value = "/allrecords", method = RequestMethod.GET)
	public ResponseEntity<List<Type>> getAllType() {
		List<Type> currencyList = typeService.getAllRecords();
		if (currencyList.isEmpty()) {
			return new ResponseEntity<List<Type>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Type>>(currencyList, HttpStatus.OK);
	}

}









