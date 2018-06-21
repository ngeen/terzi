package com.oz.tailor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item {

	@Id
	@GeneratedValue()
	private Long id;
	
	@ManyToOne
	private DressModel dressModel;
	
	@ManyToOne
	private Basket basket;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DressModel getDressModel() {
		return dressModel;
	}

	public void setDressModel(DressModel dressModel) {
		this.dressModel = dressModel;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}
}
