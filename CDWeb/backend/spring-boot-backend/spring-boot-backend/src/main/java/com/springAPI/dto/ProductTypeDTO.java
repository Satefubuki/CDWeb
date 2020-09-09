package com.springAPI.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springAPI.entity.OrderProductEntity;

public class ProductTypeDTO {
	private long id;
	private String typeName;
	
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
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
