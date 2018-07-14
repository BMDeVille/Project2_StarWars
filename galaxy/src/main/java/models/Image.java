/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package models;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="IMAGES")
public class Image {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="IMAGE", nullable = false)
	private Blob image;
	
	@OneToOne
	private User myUser;
	
	@ManyToOne
	private Post myPost;
	
	//no args
	public Image() {
		
	}
	
	//no id
	public Image(Blob image) {
		super();
		this.image = image;
	}
	
	//all members
	public Image(int id, Blob image) {
		super();
		this.id = id;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public Blob getImage() {
		return image;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", image=" + image + "]";
	}
	
	
}
