package com.springAPI.responsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springAPI.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	@Query(value ="SELECT * FROM user", nativeQuery = true)
	List<UserEntity> findAll();
	
	
	UserEntity findByUserName(String username);
	
	@Query(value ="SELECT * FROM user u JOIN order_product op JOIN c_order o where u.cart_id = op.card_id and op.order_id =?1", nativeQuery = true)
	UserEntity findByOrderId(long id);
	
	
}
