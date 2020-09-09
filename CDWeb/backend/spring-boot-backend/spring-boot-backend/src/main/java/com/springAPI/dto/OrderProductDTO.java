package com.springAPI.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderProductDTO {
	private long id;
	private String name;
	private ProductTypeDTO type;
	private double price;
	private String img;
	private String color;
	private BrandDTO brand;
	private int quantity;
	@JsonIgnore
	private CartDTO cart;
	@JsonIgnore
	private OrderDTO porder;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public ProductTypeDTO getType() {
		return type;
	}
	public void setType(ProductTypeDTO type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public BrandDTO getBrand() {
		return brand;
	}
	public void setBrand(BrandDTO brand) {
		this.brand = brand;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public CartDTO getCart() {
		return cart;
	}
	public void setCart(CartDTO cart) {
		this.cart = cart;
	}

	public OrderDTO getOrder() {
		return porder;
	}
	public void setOrder(OrderDTO porder) {
		this.porder = porder;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "tên: " + this.name +", giá: " + this.price  + ", số lượng" + this.quantity + ", màu: " + this.color + ", type" + this.type.getTypeName();
	}
	
}
