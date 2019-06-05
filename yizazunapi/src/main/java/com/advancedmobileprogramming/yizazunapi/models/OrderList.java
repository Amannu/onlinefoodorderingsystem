package com.advancedmobileprogramming.yizazunapi.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrderList {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
	@JoinColumn
	private Food food;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

    private int quantity;
    
}