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
	
	@Column(name = "slug", unique = true, nullable = false)
	private String Slug;
	
	@Column(name = "image_url", unique = true, nullable = false)
	private String ImageUrl;
	
	@Column(name = "price", nullable = false)
	private Double Price;
	
	@ManyToOne
	@JoinColumn(name = "sub_category_id")
	private SubCategory subCategory;
	
	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	private Set<OrderItem> orderItems;

	public Item() {
		orderItems = new HashSet<OrderItem>();
	}

	public Item(Integer id, String title, String descrition, String slug, String imageUrl, SubCategory subCategory, Double price) {
		orderItems = new HashSet<OrderItem>();
		Id = id;
		Title = title;
		Descrition = descrition;
		Slug = slug;
		ImageUrl = imageUrl;
		this.subCategory = subCategory;
		Price = price;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
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

	public String getSlug() {
		return Slug;
	}

	public void setSlug(String slug) {
		Slug = slug;
	}

	public String getImageUrl() {
		return ImageUrl;
	}

	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
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
	
}
