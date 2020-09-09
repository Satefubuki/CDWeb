package com.springAPI.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BrandDTO {
	private long id;
	private String brand;
	
	@JsonIgnore
	private List<ProductDTO> products; 
	
	@JsonIgnore
	private List<OrderProductDTO> oProducts;
	

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public List<ProductDTO> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}
	public List<OrderProductDTO> getoProducts() {
		return oProducts;
	}
	public void setoProducts(List<OrderProductDTO> oProducts) {
		this.oProducts = oProducts;
	}
	
	
	

}
