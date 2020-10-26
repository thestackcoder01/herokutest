package com.example.demo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int id;
	
	private String firstName;
	private String lastName;
	private String password;
	
	@Column(unique = true)
	private String email;
	
public User(User user) {
		
     	this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.password = user.getPassword();
		this.email = user.getEmail();
	}


private String getLastName() {
	// TODO Auto-generated method stub
	return lastName;
}


private String getFirstName() {
	// TODO Auto-generated method stub
	return firstName;
}


public User() {
	   
   }
	
	
public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}




	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


}


