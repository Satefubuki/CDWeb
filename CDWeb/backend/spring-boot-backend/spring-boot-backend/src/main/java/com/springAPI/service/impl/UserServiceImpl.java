package com.springAPI.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springAPI.converter.CartConverter;
import com.springAPI.converter.UserConverter;
import com.springAPI.dto.UserDTO;
import com.springAPI.entity.CartEntity;
import com.springAPI.entity.UserEntity;
import com.springAPI.responsitory.CartRepository;
import com.springAPI.responsitory.UserRepository;
import com.springAPI.service.UserService;
import com.springAPI.utils.EncrytedPasswordUtils;

@Service
@DependsOn("passwordEncoder")
public class UserServiceImpl implements UserService{
	@Autowired
	PasswordEncoder passwordEncoder;
	 
//	@Autowired
//	EncrytedPasswordUtils encrytedPasswordUtils;
//	
	@Autowired
	UserConverter userConverter;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CartConverter cartConverter;
	
	@Autowired
	CartRepository cartRepository;
	

	@Override
	public UserDTO register(UserDTO user) {
		// TODO Auto-generated method stub
		user.setActive(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		UserEntity entity = userConverter.toEntity(user);
		try {
			entity = userRepository.save(entity);
			CartEntity savedCart = cartRepository.save(new CartEntity(entity));
			entity.setCart(savedCart);
			entity = userRepository.save(entity);
			return userConverter.toDTO(entity);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
		
	}



	@Override
	public List<UserDTO> get() {
		// TODO Auto-generated method stub
		List<UserEntity> list =  userRepository.findAll();
		List<UserDTO> res = new ArrayList<UserDTO>();
		for (UserEntity ue : list) {
			res.add(userConverter.toDTO(ue));

		}
		return res;
	}



	@Override
	public UserDTO checkUserName(String username) {
		// TODO Auto-generated method stub
		
		UserEntity entity = userRepository.findByUserName(username.trim());
		UserDTO res = userConverter.toDTO(entity);
		res.setCart(cartConverter.toDTO(entity.getCart()));
		return res;
	}



	@Override
	public UserDTO updateProfile(UserDTO user) {
		// TODO Auto-generated method stub
		UserEntity entity = userConverter.toEntity(user);
		entity = userRepository.save(entity);
		return userConverter.toDTO(entity);
	}

	
}
