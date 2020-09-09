package com.springAPI.responsitory;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springAPI.entity.BrandEntity;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
	List<BrandEntity> findAll();
	
	List<BrandEntity> findByBrand(String name);
	
	@Query(value ="Select * from product join brand on product.brand_id= brand.id where brand.id?=1"
			, nativeQuery = true)
	List<BrandEntity> findByBrandId(int id);
}
