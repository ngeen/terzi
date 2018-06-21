package com.oz.tailor.DTO;

public class ItemDTO {
	private long id;
	private long dressModelId;
	private long basketId;
	private String itemType;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getDressModelId() {
		return dressModelId;
	}
	public void setDressModelId(long dressModelId) {
		this.dressModelId = dressModelId;
	}
	public long getBasketId() {
		return basketId;
	}
	public void setBasketId(long basketId) {
		this.basketId = basketId;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
}
