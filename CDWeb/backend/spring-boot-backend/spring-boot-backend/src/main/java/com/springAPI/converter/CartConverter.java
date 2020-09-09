package com.springAPI.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springAPI.dto.CartDTO;
import com.springAPI.entity.CartEntity;

@Component
public class CartConverter {
	@Autowired
	 private ModelMapper modelMapper;
	
	public CartEntity toEntity(CartDTO dto) {
		CartEntity entity = modelMapper.map(dto, CartEntity.class);
		return entity;
		
	}
	
	public CartDTO toDTO(CartEntity entity) {
		CartDTO dto = modelMapper.map(entity, CartDTO.class);
		return dto;
		
	}
}
