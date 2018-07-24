package com.p2.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SECURITY_QUESTIONS")
public class SecurityQ {
	@Id
	@Column(name="SQID")
	private int sqid;
	
	@Column(name="QUESTION", unique= true, nullable= false)
	private String question;

	public SecurityQ() {
		super();
	}

	public SecurityQ(int sqid, String question) {
		super();
		this.sqid = sqid;
		this.question = question;
	}

	@Override
	public String toString() {
		return "SecurityQ [sqid=" + sqid + ", question=" + question + "]";
	}

	public int getSqid() {
		return sqid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	
}
