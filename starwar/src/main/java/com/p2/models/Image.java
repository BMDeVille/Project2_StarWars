/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package com.p2.models;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="IMAGES")
public class Image {
	
	@Id
	@Column(name="IID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int iid;
	
	@Column(name="IMAGE", nullable = false)
	private Blob image;
	
	//no args
	public Image() {
		
	}
	
	//no id
	public Image(Blob image) {
		super();
		this.image = image;
	}
	
	//all members
	public Image(int iid, Blob image) {
		super();
		this.iid = iid;
		this.image = image;
	}

	public int getId() {
		return iid;
	}

	public Blob getImage() {
		return image;
	}

	public void setId(int iid) {
		this.iid = iid;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Image [id=" + iid + ", image=" + image + "]";
	}
	
	
}
