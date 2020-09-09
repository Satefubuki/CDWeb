package com.springAPI.responsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springAPI.entity.OrderProductEntity;
import com.springAPI.entity.ProductEntity;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Long> {
	@Query(value = "SELECT * FROM order_product op JOIN user where user.cart_id = op.card_id and user.username=?1 and op.order_id is null", nativeQuery = true)
	List<OrderProductEntity> getAllByCartId(String username);
	
	@Query(value = "SELECT * FROM order_product op where op.order_id=?1", nativeQuery = true)
	List<OrderProductEntity> findAllByOrderId(long id);
	
	OrderProductEntity findById(long id);
	
}
