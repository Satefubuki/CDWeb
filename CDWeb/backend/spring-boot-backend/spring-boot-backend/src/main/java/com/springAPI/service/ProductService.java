package com.springAPI.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springAPI.dto.ProductDTO;

public interface ProductService {
	List<ProductDTO> getProducts();
	
	ProductDTO save(ProductDTO product);
	
	Page<ProductDTO> findAll(Pageable pageable);
	
	Page<ProductDTO> findAllByType(Pageable pageable, long id);
	
	Page<ProductDTO> findAllByBrand(Pageable pageable, long id);

	
	void delete(long productId);

	ProductDTO update(ProductDTO product);
	
	ProductDTO findOne(long id);
	
	Page<ProductDTO> searchProduct(String name, Pageable pageable);

}
