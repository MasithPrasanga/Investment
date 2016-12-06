package com.investment.handler.core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investment.dto.core.TypeDto;
import com.investment.entity.core.Type;
import com.investment.service.core.TypeService;

@Component
public class TypeHandler {

	@Autowired
	private TypeService typeService = null;
	
	public boolean saveType(TypeDto typeDto){
		try{
			Type type = new Type();
			type.setType(typeDto.getType());
			long status = typeService.insert(type);
			if(status == -1){
				return false;
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean updateType(int id,TypeDto typeDto){
		boolean status = false;
		try{
			Type type = new Type();
			type.setId(id);
		    type.setType(typeDto.getType());
			status =  typeService.update(type);
			return status;
		}catch(Exception e){
			return status;
		}
	}
	
}














