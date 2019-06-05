package com.advancedmobileprogramming.yizazunapi.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Food food;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}