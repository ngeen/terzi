package com.oz.tailor.DTO;


import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oz.tailor.util.JsonDateDeserializer;

public class BasketDTO {
	private long id;
	private long customerId;
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date fittingDate;
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date fittingDate2;
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date deliveryDate;	
	private long fabricId;
	private double amount;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
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
	public long getFabricId() {
		return fabricId;
	}
	public void setFabricId(long fabricId) {
		this.fabricId = fabricId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

}
