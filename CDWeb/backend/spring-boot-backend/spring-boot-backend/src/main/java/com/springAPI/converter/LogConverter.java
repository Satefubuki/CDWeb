package com.springAPI.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springAPI.dto.CartDTO;
import com.springAPI.dto.LogDTO;
import com.springAPI.entity.CartEntity;
import com.springAPI.entity.LogEntity;

@Component
public class LogConverter {
	@Autowired
	 private ModelMapper modelMapper;
	
	public LogEntity toEntity(LogDTO dto) {
		LogEntity entity = modelMapper.map(dto, LogEntity.class);
		return entity;
		
	}
	
	public LogDTO toDTO(LogEntity entity) {
		LogDTO dto = modelMapper.map(entity, LogDTO.class);
		return dto;
		
	}
}
