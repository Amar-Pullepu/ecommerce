package com.ecommerce.models.Items;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.ecommerce.models.Account.User;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "start_date", nullable = false)
	private Timestamp StartDate;
	
	@Column(name = "order_date")
	private Timestamp OrderDate;

	@Column(name = "ordered", nullable = false)
	private Boolean Ordered;

	@Column(name = "total_amount", nullable = false)
	private Double TotalAmount;
	
	@Column(name = "amount_saved", nullable = false)
	private Double AmountSaved;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private Set<OrderItem> orderItems;

	public Order() {
		orderItems = new HashSet<OrderItem>();
	}

	public Order(User user, Boolean ordered, Double totalAmount,
			Double amountSaved) {
		orderItems = new HashSet<OrderItem>();
		this.user = user;
		Ordered = ordered;
		TotalAmount = totalAmount;
		AmountSaved = amountSaved;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getStartDate() {
		return StartDate;
	}

	@PrePersist
	public void setStartDate() {
		StartDate = Timestamp.valueOf(LocalDateTime.now());
	}

	public Timestamp getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		OrderDate = orderDate;
	}

	public Boolean getOrdered() {
		return Ordered;
	}

	public void setOrdered(Boolean ordered) {
		Ordered = ordered;
	}

	public Double getTotalAmount() {
		return TotalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		TotalAmount = totalAmount;
	}

	public Double getAmountSaved() {
		return AmountSaved;
	}

	public void setAmountSaved(Double amountSaved) {
		AmountSaved = amountSaved;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
	}
	
	public String toString() {
		return this.user.toString()+" - "+this.Id;
	}

}
