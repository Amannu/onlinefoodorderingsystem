package com.amp.apiyizazun.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String user_name;

    private String password;
    
    private String role;

}
