package com.oz.tailor.model;

import javax.persistence.Entity;

@Entity
public class Pantolon  extends Item{
	private int bel;
	private int basen;
	private int ag;
	private int dizGenisligi;
	private int paca;
	private int kavala;
	
	public int getBel() {
		return bel;
	}
	public void setBel(int bel) {
		this.bel = bel;
	}
	public int getBasen() {
		return basen;
	}
	public void setBasen(int basen) {
		this.basen = basen;
	}
	public int getAg() {
		return ag;
	}
	public void setAg(int ag) {
		this.ag = ag;
	}
	public int getDizGenisligi() {
		return dizGenisligi;
	}
	public void setDizGenisligi(int dizGenisligi) {
		this.dizGenisligi = dizGenisligi;
	}
	public int getPaca() {
		return paca;
	}
	public void setPaca(int paca) {
		this.paca = paca;
	}
	public int getKavala() {
		return kavala;
	}
	public void setKavala(int kavala) {
		this.kavala = kavala;
	}

}
