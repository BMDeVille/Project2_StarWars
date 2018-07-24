package com.p2.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.p2.models.SecurityQ;

public class SecQuesDaoImpl implements SecQuesDao {
	
	@Autowired
	private SessionFactory sesFact;
	public SecQuesDaoImpl() {
		System.out.println("secQues dao impl");
	}

	public void insertQues(SecurityQ sec_ques) {
		sesFact.getCurrentSession().save(sec_ques);
	}

}
