package com.ecommerce.repositories.Items;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.models.Items.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer>{

}
