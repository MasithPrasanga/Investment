package com.investment.handler.core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investment.dto.core.CategoryDto;
import com.investment.entity.core.Category;
import com.investment.service.core.CategoryService;

@Component
public class CategoryHandler {

	@Autowired
	private CategoryService categoryService = null;
	
	public boolean saveCategory(CategoryDto categoryDto){
		try{
			Category category = new Category();
			category.setName(categoryDto.getName());
			long status = categoryService.insert(category);
			if(status == -1){
				return false;
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean updateCategory(int id,CategoryDto categoryDto){
		boolean status = false;
		try{
			Category category = new Category();
			category.setId(id);
			category.setName(categoryDto.getName());
			status =  categoryService.update(category);
			return status;
		}catch(Exception e){
			return status;
		}
	}
	
}














