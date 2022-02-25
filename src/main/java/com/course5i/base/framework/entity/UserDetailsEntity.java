package com.course5i.base.framework.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UserDetailsEntity {
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	private String userName;
	
	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private String city;
	
	private int zipCode;
	
	private char gender;
	
	private int age;
	
	private int contactNumber;
	
	private String emailId;
	
	private String password;
	
	private boolean active;

}
