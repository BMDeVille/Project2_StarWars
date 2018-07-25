/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package com.p2.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.p2.models.User;


@Repository("UserDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//@Autowired
	//private SessionFactory sesFact = (SessionFactory) Main.context.getBean("sessionFactory");
	@Autowired
	private SessionFactory sesFact;
	public UserDAOImpl() {
		System.out.println("user dao impl");
	}
	
//	public void setSessionFactory(SessionFactory sf) {
//		this.sesFact = sf;
//	}
	public void insert(User u) {
		//Session ses = sesFact.openSession();
		
		//ses.save(u);
		System.out.println("insert");
		sesFact.getCurrentSession().save(u);
	}

	public List<User> selectAll() {
		return sesFact.getCurrentSession().createQuery("from User", User.class).list();
	}

	public User selectByUsername(String username) {
		//since username should be unique, there will be only one result
		return sesFact.getCurrentSession().createQuery("from User where username='"+username+"'", User.class).list().get(0);
	}

	public List<User> selectByFirstName(String fname) {
		return sesFact.getCurrentSession().createQuery("from User where first_name='"+fname+"'", User.class).list();
	}

	public List<User> selectByLastName(String lname) {
		return sesFact.getCurrentSession().createQuery("from User where last_name='"+lname+"'", User.class).list();
	}
	
	public User selectByEmail(String email) {
		return sesFact.getCurrentSession().createQuery("from User where email='"+email+"'", User.class).list().get(0);
	}

	public void update(User u) {
		sesFact.getCurrentSession().update(u);
	}

	public void delete(User u) {
		sesFact.getCurrentSession().delete(u);
	}

}
