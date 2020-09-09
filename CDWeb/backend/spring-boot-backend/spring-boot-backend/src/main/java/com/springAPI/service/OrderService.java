package com.springAPI.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springAPI.dto.OrderDTO;
import com.springAPI.dto.OrderProductDTO;
import com.springAPI.dto.UserDTO;

public interface OrderService {
	Page<OrderDTO> findAll(Pageable pageable);
	List<OrderDTO> getByUser(String username);
	//tạo đơn hàng với danh sách sản phẩm
	OrderDTO save(List<OrderProductDTO> oproduct, UserDTO user);
	//Thay đổi trang thái của đơn hàng
	OrderDTO changeStatus(OrderDTO order, String email);
	List<OrderProductDTO> getProductById(long id);

}
