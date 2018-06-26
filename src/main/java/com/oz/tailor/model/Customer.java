package com.oz.tailor.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oz.tailor.util.JsonDateDeserializer;

@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	
	private String customerName;
	private String customerSurname;
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date birthDay;
	private String companyName;
	private String phoneNumber;
	private String mail;
	private int weight;
	private int height;
	private int footSize;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private Set<Basket> baskets = new HashSet<Basket>();
	
	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private Set<Receipt> receipts = new HashSet<Receipt>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerSurname() {
		return customerSurname;
	}
	public void setCustomerSurname(String customerSurname) {
		this.customerSurname = customerSurname;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getFootSize() {
		return footSize;
	}
	public void setFootSize(int footSize) {
		this.footSize = footSize;
	}
	public Set<Basket> getBaskets() {
		return baskets;
	}
	public void setBaskets(Set<Basket> baskets) {
		this.baskets = baskets;
	}
	public Set<Receipt> getReceipts() {
		return receipts;
	}
	public void setReceipts(Set<Receipt> receipts) {
		this.receipts = receipts;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

}
