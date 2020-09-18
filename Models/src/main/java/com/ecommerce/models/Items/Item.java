package com.ecommerce.models.Items;

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
import javax.persistence.PostPersist;
import javax.persistence.Table;

@Entity
@Table(name = "Items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@Column(name = "title", nullable = false)
	private String Title;

	@Column(name = "description", nullable = true)
	private String Descrition;

	@Column(name = "image_url", unique = true)
	private String ImageUrl;

	@Column(name = "price", nullable = false)
	private Double Price;

	@Column(name = "discount_price")
	private Double DiscountPrice;

	@ManyToOne
	@JoinColumn(name = "sub_category_id")
	private SubCategory subCategory;

	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	private Set<OrderItem> orderItems;

	public Item() {
		orderItems = new HashSet<OrderItem>();
	}

	public Item(String title, String descrition, SubCategory subCategory, Double price, Double dPrice) {
		orderItems = new HashSet<OrderItem>();
		Title = title;
		Descrition = descrition;
		this.subCategory = subCategory;
		Price = price;
		DiscountPrice = dPrice;
	}

	public Double getDiscountPrice() {
		return DiscountPrice;
	}

	public void setDiscountPrice(Double discountPrice) {
		DiscountPrice = discountPrice;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getId() {
		return Id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescrition() {
		return Descrition;
	}

	public void setDescrition(String descrition) {
		Descrition = descrition;
	}

	public String getImageUrl() {
		return ImageUrl;
	}

	@PostPersist
	public void setImageUrl() {
		ImageUrl = "/Static/Images/Item/" + Id + ".jpg";
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void addOrderItems(OrderItem orderItem) {
		orderItems.add(orderItem);
	}

	public String toString() {
		return this.Title;
	}

}
