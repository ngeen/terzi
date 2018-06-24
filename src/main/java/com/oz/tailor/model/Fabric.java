package com.oz.tailor.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Fabric  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue()
	private long id;
	
	private String name;
	
	public Fabric() {	}
	
	public Fabric(String name) {
		this.name = name;
	}
	
	@ManyToOne
	private User user;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
