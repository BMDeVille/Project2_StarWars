/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package models;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User {
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="USERNAME", unique= true, nullable= false)
	private String username;
	
	@Column(name="FIRST_NAME", nullable= false)
	private String fname;
	
	@Column(name="LAST_NAME", nullable= false)
	private String lname;
	
	@Column(name="EMAIL", unique= true, nullable= false)
	private String email;
	
	@Column(name="PASSWORD", nullable= false)
	private String password;
	
	@Column(name="DOB")
	private Timestamp dob;
	
	@Column(name="SEC_ANS", nullable = false)
	private String securityAnswer;
	
	@Column(name="ABOUT")
	private String about;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Image image;
	
	@OneToMany(mappedBy="pid", fetch=FetchType.LAZY, cascade = CascadeType.ALL) 
	private List<Post> postList;
	
	@OneToMany(mappedBy="id", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<User> followers;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private int allegiance;
	
	//no args
	public User() {
	}
	
	//no id no about no image
	public User(String username, String fname, String lname, String email, String password, Timestamp dob,
					String securityAnswer, int allegiance) {
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
	
	//no id no about
	public User(String username, String fname, String lname, String email, String password, Timestamp dob,
					String securityAnswer, Image image, int allegiance) {
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
	
	//no id no image
	public User(String username, String fname, String lname, String email, String password, Timestamp dob,
					String securityAnswer, int allegiance, String about) {
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
	//no id
	public User(String username, String fname, String lname, String email, String password, Timestamp dob,
				String securityAnswer, Image image, int allegiance, String about) {
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
	//all members
	public User(int id, String username, String fname, String lname, String email, String password, Timestamp dob,
			String securityAnswer, Image image, int allegiance, String about) {
		super();
		this.id = id;
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

	public Image getImage() {
		return image;
	}

	public int getAllegiance() {
		return allegiance;
	}

	public String getAbout() {
		return about;
	}

	public List<Post> getPostList() {
		return postList;
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

	public void setImage(Image image) {
		this.image = image;
	}

	public void setAllegiance(int allegiance) {
		this.allegiance = allegiance;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", fname=" + fname + ", lname=" + lname + ", email="
				+ email + ", password=" + password + ", dob=" + dob + ", securityAnswer=" + securityAnswer + ", image="
				+ image + ", allegiance=" + allegiance + ", about=" + about + "]";
	}
	
	
}

