package com.ecommerce.repositories.Items;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.models.Items.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{

}
