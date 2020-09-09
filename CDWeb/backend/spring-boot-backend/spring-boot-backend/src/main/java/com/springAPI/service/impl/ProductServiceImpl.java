package com.springAPI.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.spi.Function;
import org.springframework.stereotype.Service;

import com.springAPI.converter.ProductConverter;
import com.springAPI.dto.ProductDTO;
import com.springAPI.entity.ProductEntity;
import com.springAPI.responsitory.ProductRepository;
import com.springAPI.service.FilesStorageService;
import com.springAPI.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductConverter productConverter;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	FilesStorageService filesStorageService;

	@Override
	public List<ProductDTO> getProducts() {
		// TODO Auto-generated method stub
		List<ProductDTO> res = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = productRepository.findAll();
		for (ProductEntity p : entities) {
			res.add(productConverter.toDTO(p));
		}
		return res;
	}

	@Override
	public ProductDTO save(ProductDTO product) {
		// TODO Auto-generated method stub
		ProductEntity entity = productConverter.toEntity(product);
		entity = productRepository.save(entity);
		return productConverter.toDTO(entity);
	}

	@Override
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<ProductEntity> entities = productRepository.findAll(pageable);
		Page<ProductDTO> dtoPage = entities.map(this::convertToProductDTO);
        return dtoPage;
    }
	private ProductDTO convertToProductDTO(ProductEntity entity) {
		ProductDTO dto = modelMapper.map(entity, ProductDTO.class);
	    //conversion here
	    return dto;
	}

	@Override
	public void delete(long productId) {
		// TODO Auto-generated method stub
		ProductEntity entity = productRepository.findById(productId);
		filesStorageService.deleteOne(entity.getMainImg());
		filesStorageService.deleteOne(entity.getSubImg());
		filesStorageService.deleteOne(entity.getSubImg2());
		productRepository.delete(entity);
		
	}

	@Override
	public ProductDTO update(ProductDTO product) {
		// TODO Auto-generated method stub
		ProductEntity entity = productConverter.toEntity(product);
		entity = productRepository.save(entity);
		return productConverter.toDTO(entity);
		
	}

	@Override
	public ProductDTO findOne(long id) {
		// TODO Auto-generated method stub
		ProductEntity entity = productRepository.findById(id);
		
		return productConverter.toDTO(entity);
	}

	@Override
	public Page<ProductDTO> findAllByType(Pageable pageable, long id) {
		// TODO Auto-generated method stub
		Page<ProductEntity> entities = productRepository.findAllByTypeId(pageable, id);
		Page<ProductDTO> dtoPage = entities.map(this::convertToProductDTO);
        return dtoPage;
	}

	@Override
	public Page<ProductDTO> searchProduct(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		List<ProductDTO> res = new ArrayList<>();
		Page<ProductEntity> entities = productRepository.findAllByNameContains(pageable, name);
//		for (ProductEntity productEntity : entity) {
//			res.add(productConverter.toDTO(productEntity));
//		}
		Page<ProductDTO> dtoPage = entities.map(this::convertToProductDTO);
		return dtoPage;
	}

	@Override
	public Page<ProductDTO> findAllByBrand(Pageable pageable, long id) {
		Page<ProductEntity> entities = productRepository.findAllByBrandId(pageable, id);
		Page<ProductDTO> dtoPage = entities.map(this::convertToProductDTO);
        return dtoPage;
	}

}
