package com.springAPI.responsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springAPI.entity.LogEntity;
import com.springAPI.entity.ProductEntity;

public interface LogRepository extends JpaRepository<LogEntity, Long>{


}
