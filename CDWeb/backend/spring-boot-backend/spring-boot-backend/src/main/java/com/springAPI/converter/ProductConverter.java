package com.springAPI.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springAPI.dto.ProductDTO;
import com.springAPI.dto.ProductTypeDTO;
import com.springAPI.entity.ProductEntity;
import com.springAPI.entity.ProductTypeEntity;

@Component
public class ProductConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	public ProductEntity toEntity(ProductDTO dto) {
		ProductEntity entity = modelMapper.map(dto, ProductEntity.class);
		return entity;
		
	}
	
	public ProductDTO toDTO(ProductEntity entity) {
		ProductDTO dto = modelMapper.map(entity, ProductDTO.class);
		return dto;
		
	}
}
