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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "USERNAME", unique = true, nullable = false)
	private String username;

	@Column(name = "FIRST_NAME", nullable = false)
	private String fname;

	@Column(name = "LAST_NAME", nullable = false)
	private String lname;

	@Column(name = "EMAIL", unique = true, nullable = false)
	private String email;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "DOB")
	private Timestamp dob;
	
	@Column(name = "JOIN_DATE")
	private Timestamp joined;

	@Column(name = "SEC_ANS", nullable = false)
	private String securityAnswer;

	@Column(name = "ABOUT")
	private String about;

	@Column(name = "IMAGE")
	private String image;

	@OneToMany(mappedBy = "pid", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Post> postList;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<User> followers;

	@OneToOne(fetch = FetchType.EAGER)
	private Allegiance allegiance;
	
	@OneToOne(fetch = FetchType.EAGER)
	private SecurityQ sec_ques;

	// no args
	public User() {
	}

	// no id no about no String
	public User(String username, String fname, String lname, String email, String password, Timestamp dob,
			String securityAnswer, Allegiance allegiance) {
		super();
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.securityAnswer = securityAnswer;
		this.allegiance = allegiance;
	}

	//no id no about no iamge no sec_ans
	public User(String username, String fname, String lname, String email, String password, Timestamp dob,
			 Allegiance allegiance) {
		super();
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.allegiance = allegiance;
	}

	
	
	// no id no about
	public User(String username, String fname, String lname, String email, String password, Timestamp dob,
			String securityAnswer, String image, Allegiance allegiance) {
		super();
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.securityAnswer = securityAnswer;
		this.image = image;
		this.allegiance = allegiance;
	}
	
	public User(String username, String fname, String lname, String email, String password, Timestamp dob,
			String securityAnswer, SecurityQ sec_ques, Allegiance allegiance) {
		super();
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.securityAnswer = securityAnswer;
		this.sec_ques = sec_ques;
		this.allegiance = allegiance;
	}
	
	public User(String username, String fname, String lname, String email, String password, Timestamp dob,
			String securityAnswer, SecurityQ sec_ques,Timestamp joined, Allegiance allegiance) {
		super();
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.securityAnswer = securityAnswer;
		this.sec_ques = sec_ques;
		this.joined = joined;
		this.allegiance = allegiance;
	}

	// no id no String
	public User(String username, String fname, String lname, String email, String password, Timestamp dob,
			String securityAnswer, Allegiance allegiance, String about) {
		super();
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.securityAnswer = securityAnswer;
		this.allegiance = allegiance;
		this.about = about;
	}

	// no id
	public User(String username, String fname, String lname, String email, String password, Timestamp dob,
			String securityAnswer, String image, Allegiance allegiance, String about) {
		super();
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.securityAnswer = securityAnswer;
		this.image = image;
		this.allegiance = allegiance;
		this.about = about;
	}

	// all members
	public User(int id, String username, String fname, String lname, String email, String password, Timestamp dob,
			String securityAnswer, SecurityQ sec_ques,Timestamp joined, String image, Allegiance allegiance, String about) {
		super();
		this.id = id;
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.securityAnswer = securityAnswer;
		this.sec_ques = sec_ques;
		this.joined = joined;
		this.image = image;
		this.allegiance = allegiance;
		this.about = about;
	}
	

	public User(int id, String username, String fname, String lname, String email, String password, Timestamp dob,
			String securityAnswer, String about, String image, List<User> followers, Allegiance allegiance,
			List<Comment> comments) {
		super();
		this.id = id;
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.securityAnswer = securityAnswer;
		this.about = about;
		this.image = image;
		this.followers = followers;
		this.allegiance = allegiance;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Timestamp getDob() {
		return dob;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public String getImage() {
		return image;
	}

	public Allegiance getAllegiance() {
		return allegiance;
	}

	public String getAbout() {
		return about;
	}

	public List<User> getFollowers() {
		return followers;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDob(Timestamp dob) {
		this.dob = dob;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public void setAllegiance(Allegiance allegiance) {
		this.allegiance = allegiance;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public Timestamp getJoined() {
		return joined;
	}

	public void setJoined(Timestamp joined) {
		this.joined = joined;
	}

	public SecurityQ getSec_ques() {
		return sec_ques;
	}

	public void setSec_ques(SecurityQ sec_ques) {
		this.sec_ques = sec_ques;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", fname=" + fname + ", lname=" + lname + ", email="
				+ email + ", password=" + password + ", dob=" + dob + ", joined=" + joined + ", securityAnswer="
				+ securityAnswer + ", about=" + about + ", Image=" + image + ", followers="
				+ followers + ", allegiance=" + allegiance + ", sec_ques=" + sec_ques + "]";
	}

//	@Override
//	public String toString() {
//		return "User [id=" + id + ", username=" + username + ", fname=" + fname + ", lname=" + lname + ", email="
//				+ email + ", password=" + password + ", dob=" + dob + ", securityAnswer=" + securityAnswer + ", String="
//				+ String + ", about=" + about + "]";
//	}
	
	

}
