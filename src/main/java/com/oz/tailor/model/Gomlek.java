package com.oz.tailor.model;

import javax.persistence.Entity;

@Entity
public class Gomlek extends Item {

	private int yaka;
	private int roba;
	private int gogus;
	private int gobek;
	private int basen;
	private int ispala;
	private int boy;
	private int kolBoyu;
	private int pazu;
	private int manset;
	private String nakis;
	
	public int getYaka() {
		return yaka;
	}
	public void setYaka(int yaka) {
		this.yaka = yaka;
	}
	public int getRoba() {
		return roba;
	}
	public void setRoba(int roba) {
		this.roba = roba;
	}
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
	public int getBasen() {
		return basen;
	}
	public void setBasen(int basen) {
		this.basen = basen;
	}
	public int getIspala() {
		return ispala;
	}
	public void setIspala(int ispala) {
		this.ispala = ispala;
	}
	public int getBoy() {
		return boy;
	}
	public void setBoy(int boy) {
		this.boy = boy;
	}
	public int getKolBoyu() {
		return kolBoyu;
	}
	public void setKolBoyu(int kolBoyu) {
		this.kolBoyu = kolBoyu;
	}
	public int getPazu() {
		return pazu;
	}
	public void setPazu(int pazu) {
		this.pazu = pazu;
	}
	public int getManset() {
		return manset;
	}
	public void setManset(int manset) {
		this.manset = manset;
	}
	public String getNakis() {
		return nakis;
	}
	public void setNakis(String nakis) {
		this.nakis = nakis;
	}

}
