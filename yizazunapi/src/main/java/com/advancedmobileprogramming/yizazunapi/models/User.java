package com.advancedmobileprogramming.yizazunapi.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToMany()
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    
    @OneToOne(fetch = FetchType.LAZY,cascade =  CascadeType.ALL,mappedBy = "user")
	private OrderList order_list;

}