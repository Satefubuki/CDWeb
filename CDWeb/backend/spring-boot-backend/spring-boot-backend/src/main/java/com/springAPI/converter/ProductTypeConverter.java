package com.springAPI.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springAPI.dto.ProductTypeDTO;
import com.springAPI.entity.ProductTypeEntity;

@Component
public class ProductTypeConverter {
	@Autowired
	 private ModelMapper modelMapper;
	
	public ProductTypeEntity toEntity(ProductTypeDTO roledto) {
		ProductTypeEntity entity = modelMapper.map(roledto, ProductTypeEntity.class);
//		RoleEntity entity = new RoleEntity();
//		entity.setId(roledto.getId());
//		entity.setRoleName(roledto.getRoleName());
		//entity.setUsers(roledto.getUsers());
		return entity;
		
	}
	
	public ProductTypeDTO toDTO(ProductTypeEntity entity) {
		ProductTypeDTO dto = modelMapper.map(entity, ProductTypeDTO.class);
		return dto;
		
	}
}
