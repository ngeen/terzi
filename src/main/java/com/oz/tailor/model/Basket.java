package com.oz.tailor.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Basket implements Serializable {
	
	@Id
	@GeneratedValue()
	private Long id;
	
	@OneToMany(mappedBy = "basket")
	@JsonIgnore
	private List<Item> items;
	
	@ManyToOne
	private Customer customer;
	
	private Date fittingDate;
	private Date fittingDate2;
	private Date deliveryDate;
	
	@ManyToOne
	private Fabric fabric;
	
	private double amount;
	
	@ManyToOne
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getFittingDate() {
		return fittingDate;
	}

	public void setFittingDate(Date fittingDate) {
		this.fittingDate = fittingDate;
	}

	public Date getFittingDate2() {
		return fittingDate2;
	}

	public void setFittingDate2(Date fittingDate2) {
		this.fittingDate2 = fittingDate2;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Fabric getFabric() {
		return fabric;
	}

	public void setFabric(Fabric fabric) {
		this.fabric = fabric;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
