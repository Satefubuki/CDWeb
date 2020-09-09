package com.springAPI.service;

import java.util.List;

import com.springAPI.dto.CartDTO;
import com.springAPI.dto.OrderProductDTO;
import com.springAPI.dto.UserDTO;

public interface CartService {
	
	//lấy danh sách giỏ hàng của 1 user
	List<OrderProductDTO> getListProduct(String username);
	
	CartDTO getCart(UserDTO user);
	
	//Lưu sản phẩm vào giỏ hàng
	OrderProductDTO saveProduct(OrderProductDTO productInOrder);

	//Liên kết giỏ hàng khi đã đăng nhập với giỏ hàng đã lưu trên localStorage
    void mergeLocalCart(List<OrderProductDTO> productInOrders, UserDTO user);

    //Xóa sản phẩm ra khỏi giỏ
    void delete(long id);

    void checkout(UserDTO user);

    
}
