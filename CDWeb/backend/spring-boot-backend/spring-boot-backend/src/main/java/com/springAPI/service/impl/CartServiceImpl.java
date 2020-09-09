package com.springAPI.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springAPI.converter.CartConverter;
import com.springAPI.converter.OrderProductConverter;
import com.springAPI.dto.CartDTO;
import com.springAPI.dto.OrderProductDTO;
import com.springAPI.dto.UserDTO;
import com.springAPI.entity.OrderProductEntity;
import com.springAPI.responsitory.OrderProductRepository;
import com.springAPI.service.CartService;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	CartConverter cartConverter;
	
	@Autowired 
	OrderProductConverter orderProductConverter;
	
	@Autowired
	OrderProductRepository orderProductRepository;

	@Override
	public CartDTO getCart(UserDTO user) {
		// TODO Auto-generated method stub
		
		return null;
	}

	
	@Override
	public void mergeLocalCart(List<OrderProductDTO> productInOrders, UserDTO user) {
		// TODO Auto-generated method stub
		//List<OrderProductEntity> entities = new ArrayList<OrderProductEntity>();
		List<OrderProductEntity> entities = orderProductRepository.getAllByCartId(user.getUserName());
		//Nếu người này chưa có hàng trong giỏ thì hợp nhất
		if(entities.size() == 0) {
		for (OrderProductDTO dto : productInOrders) {
			dto.setCart(user.getCart());
			System.out.println(dto.getCart().getId());

			OrderProductEntity entity = orderProductConverter.toEntity(dto);
			entity = orderProductRepository.save(entity);
		//	entities.add(entity);
		}
		}
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		OrderProductEntity entity = orderProductRepository.findById(id);
		orderProductRepository.delete(entity);
		
	}

	@Override
	public void checkout(UserDTO user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrderProductDTO> getListProduct(String username) {
		// TODO Auto-generated method stub
		List<OrderProductDTO> res = new ArrayList<OrderProductDTO>();
		List<OrderProductEntity> entities =  orderProductRepository.getAllByCartId(username);
		for (OrderProductEntity orderProductEntity : entities) {
			res.add(orderProductConverter.toDTO(orderProductEntity));
		}
		
		return res;
	}


	@Override
	public OrderProductDTO saveProduct(OrderProductDTO productInOrder) {
		// TODO Auto-generated method stub
		OrderProductEntity entity = orderProductConverter.toEntity(productInOrder);
		entity = orderProductRepository.save(entity);
		return orderProductConverter.toDTO(entity);
	}

}
