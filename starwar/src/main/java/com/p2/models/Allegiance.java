package com.p2.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ALLEGIANCES")
public class Allegiance {
	@Id
	@Column(name="AID")
	private int aid;
	
	@Column(name="ALLEGIANCE", unique= true, nullable= false)
	private String allegiance;
	
	//no args
	public Allegiance() {
		
	}
	
	//all members
	public Allegiance(int aid, String allegiance) {
		super();
		this.aid = aid;
		this.allegiance = allegiance;
	}


	public int getAid() {
		return aid;
	}

	public String getAllegiance() {
		return allegiance;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public void setAllegiance(String allegiance) {
		this.allegiance = allegiance;
	}

	@Override
	public String toString() {
		return "Allegiance [aid=" + aid + ", allegiance=" + allegiance + "]";
	}
	
	
}
