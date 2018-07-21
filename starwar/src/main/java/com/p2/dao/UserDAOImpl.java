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
<<<<<<< HEAD:starwar/src/main/java/dao/UserDAOImpl.java
	
=======
	//@Autowired
	//private SessionFactory sesFact = (SessionFactory) Main.context.getBean("sessionFactory");
>>>>>>> b8e1dede65fe101e6feaa03e979101d50f6c34b3:starwar/src/main/java/com/p2/dao/UserDAOImpl.java
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
		return sesFact.getCurrentSession().createQuery("from User", User.class).list().get(0);
	}

	public List<User> selectByFirstName(String fname) {
		return sesFact.getCurrentSession().createQuery("from User where fname="+fname, User.class).list();
	}

	public List<User> selectByLastName(String lname) {
		return sesFact.getCurrentSession().createQuery("from User where lname="+lname, User.class).list();
	}

	public void update(User u) {
		sesFact.getCurrentSession().update(u);
	}

	public void delete(User u) {
		sesFact.getCurrentSession().delete(u);
	}

}
