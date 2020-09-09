package com.springAPI.dto;

public class ProductDTO {
	private long id;
	private String name;
	private ProductTypeDTO type;
	private double price;
	private String color;
	private BrandDTO brand;
	private int stock;
	private int sale;
	private String mainImg;
	private String subImg;
	private String subImg2;
	
	
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
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getSale() {
		return sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
	public String getMainImg() {
		return mainImg;
	}
	public void setMainImg(String mainImg) {
		if(mainImg == null) {
			mainImg = "";
		}
		this.mainImg = mainImg;
	}
	public String getSubImg() {
		return subImg;
	}
	public void setSubImg(String subImg) {
		if(subImg == null) {
			subImg = "";
		}
		this.subImg = subImg;
	}
	public String getSubImg2() {
		return subImg2;
	}
	public void setSubImg2(String subImg2) {
		if(subImg2 == null) {
			subImg2 = "";
		}
		this.subImg2 = subImg2;
	}
	
	
	
}
