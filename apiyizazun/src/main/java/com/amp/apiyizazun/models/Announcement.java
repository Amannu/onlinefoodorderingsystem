package com.amp.apiyizazun.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Announcement {
	@Id
    private int id;
	
	private String subject;

	private String description;
}
