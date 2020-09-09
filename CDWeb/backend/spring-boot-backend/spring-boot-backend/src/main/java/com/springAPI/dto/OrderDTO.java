package com.springAPI.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderDTO {
	  private Long id;
	  private String buyerName;
	  private String buyerAddress;
	  private long orderAmount;
	  private int orderStatus;
	  private Date createTime;
	  @JsonIgnore
	  private List<OrderProductDTO> products = new ArrayList<>();

	  
	  public OrderDTO() {
		// TODO Auto-generated constructor stub
	}
	  public OrderDTO(UserDTO buyer) {
	        this.buyerName = buyer.getFullName();
	        this.buyerAddress = buyer.getAddress();
	        this.orderStatus = 0;
	        long total = 0;
	        for (OrderProductDTO orderProductDTO : buyer.getCart().getProducts()) {
				total += orderProductDTO.getPrice() * orderProductDTO.getQuantity();
			}
	        this.orderAmount = total;

	    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerAddress() {
		return buyerAddress;
	}
	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}
	public long getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(long orderAmount) {
		this.orderAmount = orderAmount;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<OrderProductDTO> getProducts() {
		return products;
	}
	public void setProducts(List<OrderProductDTO> products) {
		this.products = products;
	}
	
	
	  
}

