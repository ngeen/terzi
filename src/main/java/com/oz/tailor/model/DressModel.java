package com.oz.tailor.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DressModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue()
	private long id;
	
	@ManyToOne
	private DressType dressType;
	
	private String dressModel;
	
	public DressModel() {	}
	
	public DressModel(DressType dressType, String dressModel) {
			this.dressModel = dressModel;
			this.dressType = dressType;
	}

	public long getId() {
		return id; 
	}

	public void setId(long id) {
		this.id = id;
	}

	public DressType getDressType() {
		return dressType;
	}

	public void setDressType(DressType dressType) {
		this.dressType = dressType;
	}

	public String getDressModel() {
		return dressModel;
	}

	public void setDressModel(String dressModel) {
		this.dressModel = dressModel;
	}

}
