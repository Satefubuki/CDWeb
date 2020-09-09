package com.springAPI.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.springAPI.converter.CartConverter;
import com.springAPI.converter.OrderConverter;
import com.springAPI.converter.OrderProductConverter;
import com.springAPI.dto.OrderDTO;
import com.springAPI.dto.OrderProductDTO;
import com.springAPI.dto.ProductDTO;
import com.springAPI.dto.UserDTO;
import com.springAPI.entity.OrderEntity;
import com.springAPI.entity.OrderProductEntity;
import com.springAPI.entity.ProductEntity;
import com.springAPI.entity.UserEntity;
import com.springAPI.responsitory.OrderProductRepository;
import com.springAPI.responsitory.OrderRepository;
import com.springAPI.responsitory.UserRepository;
import com.springAPI.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderProductConverter orderProductConverter;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderConverter orderConverter;
	@Autowired
	OrderProductRepository orderProductRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	CartConverter cartConverter;

	@Override
	public Page<OrderDTO> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<OrderEntity> entities = orderRepository.findAll(pageable);
		Page<OrderDTO> dtoPage = entities.map(this::convertToOrderDTO);
		return dtoPage;
	}

	private OrderDTO convertToOrderDTO(OrderEntity entity) {
		OrderDTO dto = modelMapper.map(entity, OrderDTO.class);
		// conversion here
		return dto;
	}

	@Override
	public List<OrderDTO> getByUser(String username) {
		// TODO Auto-generated method stub
		List<OrderEntity> entities = orderRepository.getByUser(username);
		List<OrderDTO> dtos = new ArrayList<>();
		for (OrderEntity entity : entities) {
			dtos.add(orderConverter.toDTO(entity));
		}
		return dtos;
	}

	@Override
	public OrderDTO save(List<OrderProductDTO> oproduct, UserDTO user) {
		// TODO Auto-generated method stub
		OrderDTO order = new OrderDTO(user);
		List<OrderProductEntity> list = new ArrayList<OrderProductEntity>();
		for (OrderProductDTO orderProductDTO : oproduct) {
			OrderProductEntity entity = orderProductConverter.toEntity(orderProductDTO);
			//entity.setCart(cartConverter.toEntity(user.getCart()));
			list.add(entity);
		}
		//order.setProducts(oproduct);

		OrderEntity entity = orderRepository.save(orderConverter.toEntity(order));
		for (OrderProductEntity orderProductEntity : list) {
			orderProductEntity.setOrder(entity);
			orderProductRepository.save(orderProductEntity);
		}

		return order;
	}

	@Override
	public List<OrderProductDTO> getProductById(long id) {
		// TODO Auto-generated method stub
		List<OrderProductDTO> dtos = new ArrayList<>();
		List<OrderProductEntity> entities = orderProductRepository.findAllByOrderId(id);
		for (OrderProductEntity orderProductEntity : entities) {
			dtos.add(orderProductConverter.toDTO(orderProductEntity));
		}
		return dtos;
	}

	@Override
	public OrderDTO changeStatus(OrderDTO order, String email) {
		// TODO Auto-generated method stub
		OrderEntity entity = orderRepository.save(orderConverter.toEntity(order));
		List<OrderProductEntity> list = orderProductRepository.findAllByOrderId(order.getId());
		UserEntity user = userRepository.findByOrderId(order.getId());
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(user.getEmail());
		message.setSubject("LD thông báo");
		String mes = "";
		if (order.getOrderStatus() == 1) {
			mes += "\n Xin chào anh/chị " + order.getBuyerName() + ",";
			mes += "\n Đơn hàng của quý khách bao gồm: ";
			for (OrderProductEntity o : list) {
				mes += "\n" + o.getName() + ", số lượng " + o.getQuantity();
			}
			mes += "\n Tổng giá trị đơn hàng: " + order.getOrderAmount();
			mes += "\n Đơn hàng của quý khác đã được xử lý và sẽ chuyển giao trong vài ngày tới";
		}
		if (order.getOrderStatus() == 2) {
			mes += "\n Xin chào anh/chị " + order.getBuyerAddress() + ",";
			mes += "\n Đơn hàng của quý khách bao gồm: ";
			for (OrderProductEntity o : list) {
				mes += "\n " + o.getName() + ", số lượng " + o.getQuantity();
			}
			mes += "\n Vì một số lý do mà đơn hàng này không thể thực hiện được.";
			mes += "\n Chúng tôi vô cùng xin lỗi vệ sự cố trên.";
		}
		mes += "\n Chân thành cảm ơn quý khác đã sử dụng dịch vụ của chúng tôi";
		// Send Message!
		message.setText(mes);
		this.javaMailSender.send(message);
		return orderConverter.toDTO(entity);
	}

}
