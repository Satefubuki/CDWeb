package com.springAPI.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.springAPI.dto.UserDTO;
import com.springAPI.model.*;
import com.springAPI.security.jwt.JwtProvider;
import com.springAPI.service.UserService;


@RestController
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtProvider jwtProvider;
	
	@GetMapping("/user")
	public List<UserDTO> get() {
		return userService.get();
	}
	
	@GetMapping("/user/check")
	public Result<UserDTO>  check(@RequestParam("username") String username) {
		try {
			UserDTO res = userService.checkUserName(username);
			return new Result<>(306, res);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result<>(0);
		}
	}
	@PostMapping("/register")
	public UserDTO add(@RequestBody UserDTO user) {
		return userService.register(user);
	}
	
	@PutMapping("/update")
	public UserDTO update(@RequestBody UserDTO user) {
		return userService.updateProfile(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
		try {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
		System.out.println(authentication);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generate(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserDTO user = userService.checkUserName(userDetails.getUsername());
		JwtResponse jwtResponse = new JwtResponse(jwt, user.getUserName(), user.getFullName(), user.getRole());
		return new ResponseEntity<>( new Result<JwtResponse>(0, jwtResponse), HttpStatus.OK);
		
        } catch (AuthenticationException e) {
		return new ResponseEntity<>( new Result<>(401), HttpStatus.OK);
        }
		
	}
	
	//https://loda.me/huong-dan-spring-security-jwt-json-web-token-hibernate-loda1556683105052/
}
