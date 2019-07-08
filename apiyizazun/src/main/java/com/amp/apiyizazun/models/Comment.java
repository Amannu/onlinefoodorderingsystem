package com.amp.apiyizazun.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String subject;

    private String description;


}
