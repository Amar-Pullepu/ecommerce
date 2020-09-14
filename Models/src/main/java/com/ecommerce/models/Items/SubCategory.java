package com.ecommerce.models.Items;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SubCategories")
public class SubCategory {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@Column(name = "sub_category", nullable = false)
	private String SubCategory;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
	private Set<Item> items;

	public SubCategory() {
		items = new HashSet<Item>();
	}

	public SubCategory(Integer id, String subCategory, Category category) {
		items = new HashSet<Item>();
		Id = id;
		SubCategory = subCategory;
		this.category = category;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getSubCategory() {
		return SubCategory;
	}

	public void setSubCategory(String subCategory) {
		SubCategory = subCategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void addItem(Item item) {
		items.add(item);
	}
	
	public Set<Item> getItems(){
		return items;
	}
	
}
