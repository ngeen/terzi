package com.oz.tailor.DTO;

import com.oz.tailor.model.Customer;

public class ReceiptDTO {
	private long id;
	
	private double receiptAmount;

	private long receiptTypeId;
	
	private long customerId;
	
	private double customerDebt;
	
	private Customer customer;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getReceiptAmount() {
		return receiptAmount;
	}

	public void setReceiptAmount(double receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	public long getReceiptTypeId() {
		return receiptTypeId;
	}

	public void setReceiptTypeId(long receiptTypeId) {
		this.receiptTypeId = receiptTypeId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getCustomerDebt() {
		return customerDebt;
	}

	public void setCustomerDebt(double customerDebt) {
		this.customerDebt = customerDebt;
	}
}
