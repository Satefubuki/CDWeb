package com.springAPI.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springAPI.dto.ProductTypeDTO;
import com.springAPI.service.ProductTypeService;

@RestController
public class ProductTypeController {

	@Autowired
	ProductTypeService productTypeService;
	@GetMapping("/product-type")
	public List<ProductTypeDTO> get() {
		return productTypeService.getTypes();
	}
	
	@PostMapping("/product-type")
	public ProductTypeDTO add(@RequestBody ProductTypeDTO type) {
		return productTypeService.save(type);
	}
}
