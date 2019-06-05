package com.advancedmobileprogramming.yizazunapi.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String food_name;

    private String food_description;

    private int price;
    
    @Lob
    private byte[] food_image;
    
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private Set<OrderList> orderlist;

    
}