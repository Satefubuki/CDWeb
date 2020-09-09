package com.springAPI.responsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springAPI.entity.ProductTypeEntity;

public interface ProductTypeRepository  extends JpaRepository<ProductTypeEntity, Long> {
	List<ProductTypeEntity> findAll();
}
