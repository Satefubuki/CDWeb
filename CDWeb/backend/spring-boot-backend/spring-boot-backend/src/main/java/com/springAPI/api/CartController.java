package com.springAPI.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springAPI.converter.BrandConverter;
import com.springAPI.converter.CartConverter;
import com.springAPI.converter.ProductTypeConverter;
import com.springAPI.converter.UserConverter;
import com.springAPI.dto.CartDTO;
import com.springAPI.dto.OrderProductDTO;
import com.springAPI.dto.ProductDTO;
import com.springAPI.dto.UserDTO;
import com.springAPI.entity.UserEntity;
import com.springAPI.model.Result;
import com.springAPI.responsitory.UserRepository;
import com.springAPI.service.CartService;
import com.springAPI.service.UserService;

@RestController
public class CartController {

	@Autowired
	UserService userService;
	@Autowired
	CartService cartService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserConverter userConverter;
	@Autowired
	CartConverter cartConverter;
	@Autowired
	BrandConverter brandConverter;
	@Autowired
	ProductTypeConverter productTypeConverter;

	@GetMapping("cart")
	public Result<List<OrderProductDTO>> getAll(@RequestParam("username") String username) {
		List<OrderProductDTO> dtos = cartService.getListProduct(username);
		return new Result<List<OrderProductDTO>>(0, dtos);
	}
	
	@PostMapping("cart")
	public Result<OrderProductDTO> addUserCart(@RequestParam("username") String username, @RequestBody(required = false) ProductDTO product) {
		List<OrderProductDTO> dtos = cartService.getListProduct(username);
		UserDTO user = userService.checkUserName(username);
		for (OrderProductDTO orderProductDTO : dtos) {
			//Kiểm tra nếu hàng đã có trong giỏ rồi thì chỉ cập nhật số lượng
			if(product.getName().trim().equals(orderProductDTO.getName().trim())) {
				orderProductDTO.setQuantity(orderProductDTO.getQuantity() + 1);
				OrderProductDTO dto = cartService.saveProduct(orderProductDTO);
				return new Result<OrderProductDTO>(0, dto);
			}
		}
		//Nếu chưa có thì tạo mới
		OrderProductDTO dto = new OrderProductDTO();
		dto.setName(product.getName());
		dto.setQuantity(1);
		dto.setImg(product.getMainImg());
		dto.setColor(product.getColor());
		dto.setType(product.getType());
		dto.setBrand(product.getBrand());
		dto.setCart(user.getCart());
		dto.setPrice(product.getPrice());
		dto = cartService.saveProduct(dto);
		
		return new Result<OrderProductDTO>(0, dto);
	}
	
//	@GetMapping("check")
//	public Result<OrderProductDTO> check(@RequestParam("productName") String username) {
//
//	}
	
	//hợp nhất giỏ hàng trc khi đăng nhập
	@PostMapping("/cart/merge")
	public Result<CartDTO> add(@RequestBody(required = false) List<OrderProductDTO> productInOrders, @RequestParam("username") String username) {
		System.out.println("merge " + username);
		UserDTO user = userService.checkUserName(username);
		if (productInOrders != null) {
			for (OrderProductDTO orderProductDTO : productInOrders) {
				System.out.println(orderProductDTO);
			}
		}
		cartService.mergeLocalCart(productInOrders, user);

		return new Result<>(0);
	}
	
	//xóa ra khỏi giỏ
	@DeleteMapping("/cart/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long productId) {
		cartService.delete(productId);
		return ResponseEntity.ok().build();
	}
}
