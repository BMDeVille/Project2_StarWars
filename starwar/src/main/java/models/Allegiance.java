/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 15 Jul 2018
 * -------------------------------------------------
 */

package models;

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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int aid;
	
	@Column(name="ALLEGIANCE", unique= true, nullable= false)
	private String allegiance;
	
	//no args
	public Allegiance() {
		
	}

	//no id
	public Allegiance(String allegiance) {
		super();
		this.allegiance = allegiance;
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
