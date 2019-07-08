package com.amp.apiyizazun.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrderList {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String item_name;

    private String full_name;
    
    private String adress;

   

}
