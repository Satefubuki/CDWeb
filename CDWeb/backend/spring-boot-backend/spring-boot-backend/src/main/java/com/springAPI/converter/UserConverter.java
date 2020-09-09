package com.springAPI.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springAPI.dto.UserDTO;
import com.springAPI.entity.UserEntity;

@Component
public class UserConverter {
	@Autowired
	 private ModelMapper modelMapper;
	
	public UserEntity toEntity(UserDTO dto) {
		UserEntity entity = modelMapper.map(dto, UserEntity.class);
		return entity;
		
	}
	
	public UserDTO toDTO(UserEntity entity) {
		UserDTO dto = modelMapper.map(entity, UserDTO.class);
		return dto;
		
	}
}
