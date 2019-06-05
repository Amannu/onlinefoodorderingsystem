package com.advancedmobileprogramming.yizazunapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.advancedmobileprogramming.yizazunapi.models.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
