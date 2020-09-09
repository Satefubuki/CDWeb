package com.springAPI.service;

import java.util.List;

import com.springAPI.dto.ProductTypeDTO;

public interface ProductTypeService {
	List<ProductTypeDTO> getTypes();
	
	ProductTypeDTO save(ProductTypeDTO productType);
} 
