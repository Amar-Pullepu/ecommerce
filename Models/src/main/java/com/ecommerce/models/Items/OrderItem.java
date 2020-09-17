package com.ecommerce.models.Items;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ecommerce.models.Account.User;

@Entity
@Table(name = "order_items")
public class OrderItem {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinTable(name = "order_order_item",
				joinColumns = @JoinColumn(name = "order_id"), 
				inverseJoinColumns = @JoinColumn(name = "order_item_id")
			)
	private Order order;

	@Column(name = "ordered", nullable = false)
	private Boolean Ordered;
	
	@Column(name = "quantity", nullable = false)
	private Integer Quantity;
	
	@Column(name = "price", nullable = false)
	private Double Price;
	
	@Column(name = "discounted_price", nullable = true)
	private Double DiscountedPrice;
	
	

	public OrderItem(Item item, User user, Boolean ordered, Integer quantity, Double price,
			Double discountedPrice, Order order) {
		this.item = item;
		this.user = user;
		Ordered = ordered;
		Quantity = quantity;
		Price = price;
		DiscountedPrice = discountedPrice;
		this.order = order;
	}

	public OrderItem() {
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getOrdered() {
		return Ordered;
	}

	public void setOrdered(Boolean ordered) {
		Ordered = ordered;
	}

	public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
	}

	public Double getDiscountedPrice() {
		return DiscountedPrice;
	}

	public void setDiscountedPrice(Double discountedPrice) {
		DiscountedPrice = discountedPrice;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public String toString() {
		return this.item.toString()+" - "+this.Id;
	}
}
