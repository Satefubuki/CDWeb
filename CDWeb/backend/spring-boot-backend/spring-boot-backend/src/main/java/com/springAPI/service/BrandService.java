package com.springAPI.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;

import com.springAPI.dto.BrandDTO;


public interface BrandService {
	List<BrandDTO> getBrands();
	
	List<BrandDTO> getBrandByname(String name);

	List<BrandDTO> getBrandByID(int id);
	
}
