package com.amp.apiyizazun.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Food {
	@Id
    private int id;
	
	private String name;

	private String content;

}
