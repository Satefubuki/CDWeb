package com.springAPI.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springAPI.converter.ProductTypeConverter;
import com.springAPI.dto.ProductTypeDTO;
import com.springAPI.entity.ProductTypeEntity;
import com.springAPI.responsitory.ProductTypeRepository;
import com.springAPI.service.ProductTypeService;

@Service
public class ProductTypeImpl implements ProductTypeService {
	@Autowired
	ProductTypeRepository productTypeRepository;
	@Autowired
	ProductTypeConverter productTypeConverter;
	
	@Override
	public List<ProductTypeDTO> getTypes() {
		// TODO Auto-generated method stub
		List<ProductTypeDTO> res = new ArrayList<ProductTypeDTO>();
		List<ProductTypeEntity> entities = productTypeRepository.findAll();
		for (ProductTypeEntity p : entities) {
			res.add(productTypeConverter.toDTO(p));
		}
		
		return res;
	}

	@Override
	public ProductTypeDTO save(ProductTypeDTO productType) {
		// TODO Auto-generated method stub
		ProductTypeEntity entity = productTypeConverter.toEntity(productType);
		entity = productTypeRepository.save(entity);
		return productTypeConverter.toDTO(entity);
	}

}
