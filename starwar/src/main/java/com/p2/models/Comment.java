/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package com.p2.models;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="COMMENTS")
public class Comment {
	
	@Id
	@Column(name="CID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cid;
	
	@Column(name="BODY", nullable= false)
	private String body;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.EAGER)
	private User poster;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<User> likes;
	
	@ManyToOne(targetEntity=Post.class, fetch=FetchType.EAGER)
	private Post post;
	
	public Comment() {
		
	}

	//no id no likes
	public Comment(String body, Post post, User poster) {
		super();
		this.body = body;
		this.post = post;
		this.poster = poster;
	}
	
	//no id
	public Comment(String body, List<User> likes, Post post, User poster) {
		super();
		this.body = body;
		this.likes = likes;
		this.post = post;
		this.poster = poster;
	}
	
	//all members
	public Comment(int cid, String body, List<User> likes, Post post, User poster) {
		super();
		this.cid = cid;
		this.body = body;
		this.likes = likes;
		this.post = post;
		this.poster = poster;
	}

	

	public int getCid() {
		return cid;
	}

	public String getBody() {
		return body;
	}

	public User getPoster() {
		return poster;
	}

	public List<User> getLikes() {
		return likes;
	}

	public Post getPost() {
		return post;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setPoster(User poster) {
		this.poster = poster;
	}

	public void setLikes(List<User> likes) {
		this.likes = likes;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "Comment [cid=" + cid + ", body=" + body + ", poster=" + poster + ", post=" + post + ", likes=" + likes + "]";
	}

//	@Override
//	public String toString() {
//		return "Comment [id=" + cid + ", body=" + body + ", likes=" + likes + "]";
//	}
	
	
	
	
}

