package com.springAPI.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CartDTO {
	private long id;
	@JsonIgnore
	private UserDTO user;
	private List<OrderProductDTO> products;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<OrderProductDTO> getProducts() {
		return products;
	}
	public void setProducts(List<OrderProductDTO> products) {
		this.products = products;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public CartDTO(UserDTO user) {
		// TODO Auto-generated constructor stub
		this.user = user;
	}
	public CartDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	

}
