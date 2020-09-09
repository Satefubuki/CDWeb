package com.springAPI.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springAPI.dto.BrandDTO;
import com.springAPI.dto.ProductTypeDTO;
import com.springAPI.entity.BrandEntity;
import com.springAPI.entity.ProductTypeEntity;

@Component
public class BrandConverter {
	@Autowired
	 private ModelMapper modelMapper;
	
	public BrandEntity toEntity(BrandDTO dto) {
		BrandEntity entity = modelMapper.map(dto, BrandEntity.class);
//		RoleEntity entity = new RoleEntity();
//		entity.setId(roledto.getId());
//		entity.setRoleName(roledto.getRoleName());
		//entity.setUsers(roledto.getUsers());
		return entity;
		
	}
	public BrandDTO toDTO(BrandEntity entity) {
		BrandDTO dto = modelMapper.map(entity, BrandDTO.class);
		return dto;
		
	}
}
