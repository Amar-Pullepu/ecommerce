package com.ecommerce.repositories.Items;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.models.Items.Item;

public interface ItemRepository extends CrudRepository<Item, Integer>{

}
