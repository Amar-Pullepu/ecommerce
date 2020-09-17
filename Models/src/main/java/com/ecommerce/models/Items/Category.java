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
import javax.persistence.PostPersist;
import javax.persistence.Table;


@Entity
@Table(name = "categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@Column(name = "category", unique = true, nullable = false)
	private String Category;
	
	@Column(name = "image_url", unique = true)
	private String ImageUrl;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private Set<SubCategory> subCategories;
	
	public Category(String category) {
		subCategories = new HashSet<SubCategory>();
		Category = category;
	}

	public Category() {
		subCategories = new HashSet<SubCategory>();
	}
	
	public Integer getId() {
		return Id;
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

	@PostPersist
	public void setImageUrl() {
		ImageUrl = "/Static/Images/Category/"+this.Id+".jpg";
	}

	public void addSubCategory(SubCategory subCategory) {
		subCategories.add(subCategory);
	}
	
	public Set<SubCategory> getItems(){
		return subCategories;
	}
	
	public String toString() {
		return this.Category;
	}
	
}
