package com.ecommerce.models.Items;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@Column(name = "category", unique = true, nullable = false)
	private String Category;
	
	@Column(name = "image_url", unique = true, nullable = false)
	private String ImageUrl;
	
	@Column(name = "sulg", unique = true, nullable = false)
	private String Slug;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private Set<SubCategory> subCategories;
	
	public Category(String category, String imageUrl, String slug) {
		subCategories = new HashSet<SubCategory>();
		Category = category;
		ImageUrl = imageUrl;
		Slug = slug;
	}

	public Category() {
		subCategories = new HashSet<SubCategory>();
	}
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getImageUrl() {
		return ImageUrl;
	}

	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}

	public String getSlug() {
		return Slug;
	}

	public void setSlug(String slug) {
		Slug = slug;
	}

	public void addSubCategory(SubCategory subCategory) {
		subCategories.add(subCategory);
	}
	
	public Set<SubCategory> getItems(){
		return subCategories;
	}
	
}
