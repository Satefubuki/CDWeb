package com.springAPI.responsitory;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springAPI.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	List<ProductEntity> findAll();
	Page<ProductEntity> findAllByOrderById(Pageable pageable);
	ProductEntity findById(long id);
	
	//@Query(value = "SELECT * FROM product ")
	Page<ProductEntity> findAllByTypeId(Pageable pageable, long id);
	

	Page<ProductEntity> findAllByBrandId(Pageable pageable, long id);
	
	//search by id and name
	
	//@Query(value = "SELECT p FROM Product p where p.type_id = ?1 and p.product_name like '%?2%'")
	Page<ProductEntity>  findAllByNameContains(Pageable pageable, String name);
	
	//@Query(value = "SELECT * FROM Product p where p.type_id = :id and p.product_name like '%:name%'", nativeQuery = true)
	//List<ProductEntity> findAllByTypeIdByProductNameContains(long id, String name);
	
}
