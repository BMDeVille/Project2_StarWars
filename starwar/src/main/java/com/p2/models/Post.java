/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package com.p2.models;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="POSTS")
public class Post {
	
	@Id
	@Column(name="PID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int pid;
	
	@Column(name="BODY", nullable= false)
	private String body;
	
	@Column(name="YOUTUBE")
	private String youtube;
	
	@Column(name="CREATED_DATE", nullable= false)
	private Timestamp created;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)
	private User creator;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<User> likes;
	
	@OneToMany(mappedBy="iid", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Image> images;
	
	//no args
	public Post() {
		
	}
	//no id no like no comments no images no youtube
	public Post(String body, Timestamp created, User creator) {
		super();
		this.body = body;
		this.created = created;
		this.creator = creator;
	}
	//no id no like no comments no images
	public Post(String body, String youtube, Timestamp created, User creator) {
		super();
		this.body = body;
		this.youtube = youtube;
		this.created = created;
		this.creator = creator;
	}
	//no id no like no comments no youtube
	public Post(String body, Timestamp created, List<Image> images, User creator) {
		super();
		this.body = body;
		this.created = created;
		this.images = images;
		this.creator = creator;
	}
	//no id no likes, no comments
	public Post(String body, String youtube, Timestamp created, List<Image> images, User creator) {
		super();
		this.body = body;
		this.youtube = youtube;
		this.created = created;
		this.images = images;
		this.creator = creator;
	}
	//all members
	public Post(int pid, String body, String youtube, Timestamp created, List<User> likes,
			List<Image> images, User creator) {
		super();
		this.pid = pid;
		this.body = body;
		this.youtube = youtube;
		this.created = created;
		this.likes = likes;
		this.images = images;
		this.creator = creator;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public String getBody() {
		return body;
	}
	public String getYoutube() {
		return youtube;
	}
	public Timestamp getCreated() {
		return created;
	}
	
	public List<User> getLikes() {
		return likes;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setId(int pid) {
		this.pid = pid;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public void setLikes(List<User> likes) {
		this.likes = likes;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "Post [id=" + pid + ", body=" + body + ", youtube=" + youtube + ", created=" + created + ", likes=" + likes + ", images=" + images + "]";
	}
	
}

