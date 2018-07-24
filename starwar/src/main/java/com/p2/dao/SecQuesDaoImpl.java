package com.p2.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.p2.models.Allegiance;
import com.p2.models.SecurityQ;

@Repository("SecQuesDao")
@Transactional
public class SecQuesDaoImpl implements SecQuesDao {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Autowired
	private SessionFactory sesFact;
	public SecQuesDaoImpl() {
		System.out.println("secQues dao impl");
	}

	public void insertQues(SecurityQ sec_ques) {
		sesFact.getCurrentSession().save(sec_ques);
	}
	
	public List<SecurityQ> selectAll() {
		return sesFact.getCurrentSession().createQuery("from SecurityQ", SecurityQ.class).list();
	}
 
	
	public SecurityQ selectById(int id) {
		return sesFact.getCurrentSession().createQuery("from SecurityQ where sqid=" + id, SecurityQ.class).list().get(0);
	}
	

}
