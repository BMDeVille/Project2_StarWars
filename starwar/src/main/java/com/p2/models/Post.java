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
	
	@OneToMany(mappedBy="cid", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Comment> comments;
	
	@OneToMany(mappedBy="id", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<User> likes;
	
	@OneToMany(mappedBy="iid", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Image> images;
	
	//no args
	public Post() {
		
	}
	//no id no like no comments no images no youtube
	public Post(String body, Timestamp created) {
		super();
		this.body = body;
		this.created = created;
	}
	//no id no like no comments no images
	public Post(String body, String youtube, Timestamp created) {
		super();
		this.body = body;
		this.youtube = youtube;
		this.created = created;
	}
	//no id no like no comments no youtube
	public Post(String body, Timestamp created, List<Image> images) {
		super();
		this.body = body;
		this.created = created;
		this.images = images;
	}
	//no id no likes, no comments
	public Post(String body, String youtube, Timestamp created, List<Image> images) {
		super();
		this.body = body;
		this.youtube = youtube;
		this.created = created;
		this.images = images;
	}
	//all members
	public Post(int pid, String body, String youtube, Timestamp created, List<Comment> comments, List<User> likes,
			List<Image> images) {
		super();
		this.pid = pid;
		this.body = body;
		this.youtube = youtube;
		this.created = created;
		this.comments = comments;
		this.likes = likes;
		this.images = images;
	}
	public int getId() {
		return pid;
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
	public List<Comment> getComments() {
		return comments;
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
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public void setLikes(List<User> likes) {
		this.likes = likes;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "Post [id=" + pid + ", body=" + body + ", youtube=" + youtube + ", created=" + created + ", comments="
				+ comments + ", likes=" + likes + ", images=" + images + "]";
	}
	
}

