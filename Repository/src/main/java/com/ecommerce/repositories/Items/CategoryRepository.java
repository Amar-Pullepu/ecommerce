package com.ecommerce.repositories.Items;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.models.Items.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer>{

}
