package com.springAPI.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.springAPI.converter.LogConverter;
import com.springAPI.dto.LogDTO;
import com.springAPI.dto.ProductDTO;
import com.springAPI.entity.LogEntity;
import com.springAPI.entity.ProductEntity;
import com.springAPI.responsitory.LogRepository;
import com.springAPI.service.LogService;

@Service
public class LogServiceImpl implements LogService {
	
	@Autowired 
	LogRepository logRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	LogConverter logConverter;
	

	@Override
	public Page<LogDTO> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<LogEntity> entities = logRepository.findAll(pageable);
		Page<LogDTO> dtoPage =  entities.map(this::convertToLogDTO);
		return dtoPage;
	}
	
	private LogDTO convertToLogDTO(LogEntity entity) {
		LogDTO dto = modelMapper.map(entity, LogDTO.class);
	    //conversion here
	    return dto;
	}

	@Override
	public LogDTO save(LogDTO dto) {
		// TODO Auto-generated method stub
		LogEntity entity = logConverter.toEntity(dto);
		entity = logRepository.save(entity);
		return logConverter.toDTO(entity);
	}

}
