package com.advancedmobileprogramming.yizazunapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.advancedmobileprogramming.yizazunapi.models.OrderList;

public interface OrderRepository extends CrudRepository<OrderList, Long> {

}
