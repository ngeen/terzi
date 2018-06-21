package com.oz.tailor.model;

import javax.persistence.Entity;

@Entity
public class Ceket extends Item {
	
	private int ayna;
	private int gogus;
	private int boy;
	private int kol;
	private int ispala;
	private int belOrtasi;
	private int pazu;
	
	public int getAyna() {
		return ayna;
	}
	public void setAyna(int ayna) {
		this.ayna = ayna;
	}
	public int getGogus() {
		return gogus;
	}
	public void setGogus(int gogus) {
		this.gogus = gogus;
	}
	public int getBoy() {
		return boy;
	}
	public void setBoy(int boy) {
		this.boy = boy;
	}
	public int getKol() {
		return kol;
	}
	public void setKol(int kol) {
		this.kol = kol;
	}
	public int getIspala() {
		return ispala;
	}
	public void setIspala(int ispala) {
		this.ispala = ispala;
	}
	public int getBelOrtasi() {
		return belOrtasi;
	}
	public void setBelOrtasi(int belOrtasi) {
		this.belOrtasi = belOrtasi;
	}
	public int getPazu() {
		return pazu;
	}
	public void setPazu(int pazu) {
		this.pazu = pazu;
	}

}
