package com.springAPI.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springAPI.dto.OrderDTO;
import com.springAPI.entity.OrderEntity;

@Component
public class OrderConverter {
	@Autowired
	 private ModelMapper modelMapper;
	
	public OrderEntity toEntity(OrderDTO dto) {
		OrderEntity entity = modelMapper.map(dto, OrderEntity.class);
		return entity;
		
	}
	
	public OrderDTO toDTO(OrderEntity entity) {
		OrderDTO dto = modelMapper.map(entity, OrderDTO.class);
		return dto;
		
	}
}
