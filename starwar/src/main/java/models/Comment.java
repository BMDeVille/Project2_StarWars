/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@OneToMany(mappedBy="id", fetch=FetchType.LAZY)
	private List<User> likes;
	
	public Comment() {
		
	}

	//no id no likes
	public Comment(String body) {
		super();
		this.body = body;
	}
	
	//no id
	public Comment(String body, List<User> likes) {
		super();
		this.body = body;
		this.likes = likes;
	}
	
	//all members
	public Comment(int cid, String body, List<User> likes) {
		super();
		this.cid = cid;
		this.body = body;
		this.likes = likes;
	}

	public int getId() {
		return cid;
	}

	public String getBody() {
		return body;
	}

	public List<User> getLikes() {
		return likes;
	}

	public void setId(int cid) {
		this.cid = cid;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setLikes(List<User> likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "Comment [id=" + cid + ", body=" + body + ", likes=" + likes + "]";
	}
	
	
}