package com.amp.apiyizazun.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Drink {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String drink_name;

    private int price;

}
