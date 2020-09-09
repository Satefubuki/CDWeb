package com.springAPI.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springAPI.dto.LogDTO;
import com.springAPI.model.Result;
import com.springAPI.service.LogService;

@RestController
public class LogController {
	@Autowired
	LogService logService;

	@GetMapping("log")
	public Page<LogDTO> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "3") Integer size) {
		PageRequest request = new PageRequest(page - 1, size);
		return logService.findAll(request);
	}
	
	@PostMapping("log")
	public Result<LogDTO> add(@RequestBody LogDTO log) {
		System.out.println("loggggggggggg: " + log.getIp());
		LogDTO dto = logService.save(log);
		return new Result<LogDTO>(0, dto);
	}
}
