package com.springAPI.responsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springAPI.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long>{

}
