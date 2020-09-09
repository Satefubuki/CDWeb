package com.springAPI.responsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springAPI.entity.OrderEntity;

public interface OrderRepository  extends JpaRepository<OrderEntity, Long> {
	@Query(value = "SELECT * FROM c_order o JOIN user u where u.full_name = o.buyer_name and u.username=?1", nativeQuery = true)
	List<OrderEntity> getByUser(String username);
}
