package com.springAPI.service.impl;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.springAPI.converter.BrandConverter;
import com.springAPI.dto.BrandDTO;
import com.springAPI.entity.BrandEntity;
import com.springAPI.responsitory.BrandRepository;
import com.springAPI.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired 
	BrandRepository brandRepository ;
	@Autowired
	BrandConverter brandConverter;

	@Override
	public List<BrandDTO> getBrands() {
		// TODO Auto-generated method stub
		List<BrandDTO> res = new ArrayList<BrandDTO>();
		List<BrandEntity> entity = brandRepository.findAll();
		for (BrandEntity brandEntity : entity) {
			res.add(brandConverter.toDTO(brandEntity));
		}
		
		return res;
	}

	@Override
	public List<BrandDTO> getBrandByname(String name) {
		// TODO Auto-generated method stub
		List<BrandEntity> entity = brandRepository.findByBrand(name);
		List<BrandDTO> res = new ArrayList<>();
		
		for (BrandEntity en : entity) {
			res.add(brandConverter.toDTO(en));
		}
		
		return res;
	}

	@Override
	public List<BrandDTO> getBrandByID(int id) {
		List<BrandEntity> entities = brandRepository.findByBrandId(id);
		//convert
		List<BrandDTO> res = new ArrayList<>();
		for (BrandEntity en : entities) {
			res.add(brandConverter.toDTO(en));
		}
		
		return res;
	}
	

}
