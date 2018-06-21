package com.oz.tailor.model;

import javax.persistence.Entity;

@Entity
public class Yelek extends Item {
	
	private int gogus;
	private int gobek;
	private int ayna;
	private int boy;
	
	public int getGogus() {
		return gogus;
	}
	public void setGogus(int gogus) {
		this.gogus = gogus;
	}
	public int getGobek() {
		return gobek;
	}
	public void setGobek(int gobek) {
		this.gobek = gobek;
	}
	public int getAyna() {
		return ayna;
	}
	public void setAyna(int ayna) {
		this.ayna = ayna;
	}
	public int getBoy() {
		return boy;
	}
	public void setBoy(int boy) {
		this.boy = boy;
	}

}
