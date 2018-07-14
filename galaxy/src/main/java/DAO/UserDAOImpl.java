/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import models.User;
import util.HibernateUtil;

public class UserDAOImpl implements UserDAO {

	public void insert(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		ses.save(u);
		tx.commit();
	}

	public List<User> selectAll() {
		Session ses = HibernateUtil.getSession();
		List<User> ulist = ses.createQuery("from User", User.class).list();
		return ulist;
	}

	public List<User> selectByUsername(String username) {
		Session ses = HibernateUtil.getSession();
		List<User> ulist = ses.createQuery("from User where username=" + username, User.class).list();
		return ulist;
	}

	public List<User> selectByFirstName(String fname) {
		Session ses = HibernateUtil.getSession();
		List<User> ulist = ses.createQuery("from User where fname=" + fname, User.class).list();
		return ulist;
	}

	public List<User> selectByLastName(String lname) {
		Session ses = HibernateUtil.getSession();
		List<User> ulist = ses.createQuery("from User where lname=" + lname, User.class).list();
		return ulist;
	}

	public void update(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		ses.update(u);
		tx.commit();
	}

	public void delete(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		ses.delete(u);
		tx.commit();
	}

}
