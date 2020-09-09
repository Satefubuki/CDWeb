package com.springAPI.service;

import java.util.List;

import com.springAPI.dto.UserDTO;

public interface UserService {
	UserDTO register(UserDTO user);
	List<UserDTO> get();
	UserDTO checkUserName(String username);
	UserDTO updateProfile(UserDTO user);
}
