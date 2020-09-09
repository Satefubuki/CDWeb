package com.springAPI.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springAPI.dto.LogDTO;
import com.springAPI.dto.OrderDTO;
import com.springAPI.dto.OrderProductDTO;
import com.springAPI.dto.UserDTO;

public interface LogService {
	Page<LogDTO> findAll(Pageable pageable);
	
	LogDTO save(LogDTO dto);

}
