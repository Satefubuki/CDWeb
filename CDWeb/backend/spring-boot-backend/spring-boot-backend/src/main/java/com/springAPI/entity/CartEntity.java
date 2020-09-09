package com.springAPI.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springAPI.dto.UserDTO;

@Entity
@Table(name = "cart")
public class CartEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	@MapsId
	private UserEntity user;
	
	@JsonIgnore
	@OneToMany( mappedBy = "cart")
	List<OrderProductEntity> products;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	
	public List<OrderProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<OrderProductEntity> products) {
		this.products = products;
	}

	public CartEntity(UserEntity user) {
		// TODO Auto-generated constructor stub
		this.user = user;
	}
	
	public CartEntity() {
		// TODO Auto-generated constructor stub
	}
}
