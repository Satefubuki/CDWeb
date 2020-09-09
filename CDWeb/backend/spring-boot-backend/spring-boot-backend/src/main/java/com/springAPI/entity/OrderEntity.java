package com.springAPI.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "c_order")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String buyerName;
	@Column
	private String buyerAddress;
	@Column
	private long orderAmount;
	@Column
	private int orderStatus;
	@CreationTimestamp
	private Date createTime;
	@OneToMany( mappedBy = "porder")
	@JsonIgnore
	private List<OrderProductEntity> products = new ArrayList<>();

	public OrderEntity() {
		// TODO Auto-generated constructor stub
	}

	public OrderEntity(UserEntity buyer) {
		this.buyerName = buyer.getFullName();
		this.buyerAddress = buyer.getAddress();
		this.orderStatus = 0;
		long total = 0;
		for (OrderProductEntity orderProductEntity : buyer.getCart().getProducts()) {
			total += orderProductEntity.getPrice() * orderProductEntity.getQuantity();
		}
		this.orderAmount = total;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public List<OrderProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<OrderProductEntity> products) {
		this.products = products;
	}

	

}
