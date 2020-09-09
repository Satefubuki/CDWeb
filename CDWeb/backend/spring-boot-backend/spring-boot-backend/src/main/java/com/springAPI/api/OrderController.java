package com.springAPI.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springAPI.dto.OrderDTO;
import com.springAPI.dto.OrderProductDTO;
import com.springAPI.dto.ProductDTO;
import com.springAPI.dto.UserDTO;
import com.springAPI.model.Result;
import com.springAPI.responsitory.OrderRepository;
import com.springAPI.service.OrderService;
import com.springAPI.service.UserService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	@Autowired
	UserService userService;
	

	@GetMapping("order/user")
	public Result<List<OrderDTO>> findByUser(@RequestParam("username") String username) {
		List<OrderDTO> dtos = orderService.getByUser(username);
		return new Result<>(0, dtos );
	}
	
	@GetMapping("order/user/{id}")
	public Result<List<OrderProductDTO>> findOrderById(@PathVariable("id") long orderId) {
		List<OrderProductDTO> dtos = orderService.getProductById(orderId);
		return new Result<>(0, dtos );
	}
	
	
	@GetMapping("order")
	public Page<OrderDTO> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "3") Integer size) {
		PageRequest request = new PageRequest(page - 1, size);
		return orderService.findAll(request);
	}
	
	
	
	@PostMapping("order")
	public Result<OrderDTO> add(@RequestBody(required = false) List<OrderProductDTO> productInOrders, @RequestParam("username") String username){
		System.out.println("order: " + username);
		for (OrderProductDTO orderProductDTO : productInOrders) {
			System.out.println("order: " + orderProductDTO.getName());
			
		}
		UserDTO user = userService.checkUserName(username);
		OrderDTO dto = orderService.save(productInOrders, user);
		return new Result<>(0, dto);
	}
	
	@PutMapping("order")
	public Result<OrderDTO> changeStatus(@RequestBody OrderDTO order, @RequestParam("email") String email) {
		OrderDTO dto = orderService.changeStatus(order, email);
		return new Result<>(0, dto );
	}

}
