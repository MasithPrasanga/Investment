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

import com.investment.dto.core.CategoryDto;
import com.investment.entity.core.Category;
import com.investment.handler.core.CategoryHandler;
import com.investment.service.core.CategoryService;

@RestController
@RequestMapping("api/v1/category")
public class CategoryCotroller {
	
	@Autowired
	private CategoryService categoryService = null;
	
	@Autowired
	private CategoryHandler categoryHandler = null;

	// findById
	@RequestMapping(value = "/findbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> categoryServiceFindByCode(@PathVariable("id") int id) {
		Category currency = categoryService.findById(id);
		if (currency == null) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Category>(currency, HttpStatus.OK);
	}

	// save currency
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Void> saveCurrency(@RequestBody CategoryDto categoryDto) {
		try {
			boolean status = categoryHandler.saveCategory(categoryDto);
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
	public ResponseEntity<Category> updateCategory(@PathVariable("id") int id, @RequestBody CategoryDto categoryDto) {
		Category currency = categoryService.findById(id);
		if (currency == null) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}
		boolean status = categoryHandler.updateCategory(id, categoryDto);
		if (status) {
			return new ResponseEntity<Category>(categoryService.findById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<Category>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	// Delete Currency
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Category> deleteCategory(@PathVariable("id") int id) {
		Category category = categoryService.findById(id);
		if (category == null) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}
		boolean status = categoryService.delete(category);
		if (status) {
			return new ResponseEntity<Category>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Category>(HttpStatus.EXPECTATION_FAILED);
		}

	}

	// Delete All Records
	@RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
	public ResponseEntity<Category> deleteAllCategory() {
		boolean status = categoryService.deleteAllRecords();
		if (status) {
			return new ResponseEntity<Category>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
		}

	}

	// Get All Records
	@RequestMapping(value = "/allrecords", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getAllCategory() {
		List<Category> categoryList = categoryService.getAllRecords();
		if (categoryList.isEmpty()) {
			return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);
	}

}








