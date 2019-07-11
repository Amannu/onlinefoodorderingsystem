package com.amp.apiyizazun.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class SpecialOffers {
	@Id
    private int id;
	
	private String name;

	private String content;
}
