package com.springAPI.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springAPI.dto.CartDTO;

@Entity
@Table(name = "order_product")
public class OrderProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String name;
	@ManyToOne
	@JoinColumn(name = "type_id")
	private ProductTypeEntity p_type;
	@Column
	private double price;
	@Column
	private String img;
	@Column
	private String color;
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private BrandEntity p_brand;
	@Column
	private int quantity;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "card_id")
	private CartEntity cart;

	@ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private OrderEntity porder;
	
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

	public ProductTypeEntity getP_type() {
		return p_type;
	}

	public void setP_type(ProductTypeEntity p_type) {
		this.p_type = p_type;
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

	public BrandEntity getP_brand() {
		return p_brand;
	}

	public void setP_brand(BrandEntity p_brand) {
		this.p_brand = p_brand;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CartEntity getCart() {
		return cart;
	}

	public void setCart(CartEntity cart) {
		this.cart = cart;
	}

	public OrderEntity getOrder() {
		return porder;
	}

	public void setOrder(OrderEntity porder) {
		this.porder = porder;
	}

	
}
