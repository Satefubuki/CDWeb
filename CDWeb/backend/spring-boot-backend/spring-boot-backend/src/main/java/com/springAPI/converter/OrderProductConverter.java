package com.springAPI.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springAPI.dto.OrderProductDTO;
import com.springAPI.dto.ProductDTO;
import com.springAPI.entity.OrderProductEntity;
import com.springAPI.entity.ProductEntity;

@Component
public class OrderProductConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	public OrderProductEntity toEntity(OrderProductDTO dto) {
		OrderProductEntity entity = modelMapper.map(dto, OrderProductEntity.class);
		return entity;
		
	}
	
	public OrderProductDTO toDTO(OrderProductEntity entity) {
		OrderProductDTO dto = modelMapper.map(entity, OrderProductDTO.class);
		return dto;
		
	}
}
