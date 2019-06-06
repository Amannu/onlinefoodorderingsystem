package com.advancedmobileprogramming.yizazunapi.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user_name;

    private String phone_number;

    private String address;

    private String password;

    private String role;
    
    @OneToOne(fetch = FetchType.LAZY,cascade =  CascadeType.ALL,mappedBy = "user")
	private OrderList order_list;

}