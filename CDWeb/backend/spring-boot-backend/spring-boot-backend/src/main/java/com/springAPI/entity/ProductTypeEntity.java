package com.springAPI.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product_type")
public class ProductTypeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private String typeName;
	
	@JsonIgnore
	@OneToMany( mappedBy = "type")
	List<ProductEntity> products;
	
	@JsonIgnore
	@OneToMany( mappedBy = "p_type")
	List<OrderProductEntity> oProducts;
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

	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}

	public List<OrderProductEntity> getoProducts() {
		return oProducts;
	}

	public void setoProducts(List<OrderProductEntity> oProducts) {
		this.oProducts = oProducts;
	}
	

	
	
}
