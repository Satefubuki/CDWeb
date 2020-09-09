package com.springAPI.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springAPI.dto.BrandDTO;
import com.springAPI.service.BrandService;

@RestController
public class BrandController {
	@Autowired
	BrandService brandService;
	
	@GetMapping("/product-brand")
	public List<BrandDTO> get() {
		return brandService.getBrands();
	}
	
	@GetMapping("/product-brand/{name}")
	public List<BrandDTO> getName(@PathVariable("name") String name) {
		return brandService.getBrandByname(name);
	}
	@GetMapping("/product-brand/{id}")
	public List<BrandDTO> getBrandByID(@PathVariable("id") int id) {
		return brandService.getBrandByID(id);
	}
	
}
