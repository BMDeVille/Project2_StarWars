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

import models.Post;
import models.User;
import util.HibernateUtil;

public class PostDAOImpl implements PostDAO {

	public void insert(Post p) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		ses.save(p);
		tx.commit();
	}

	public List<Post> selectAll() {
		Session ses = HibernateUtil.getSession();
		List<Post> plist = ses.createQuery("from Post", Post.class).list();
		return plist;
	}

	public List<Post> selectByUser(User u) {
		Session ses = HibernateUtil.getSession();
		List<Post> plist = ses.createQuery("from Post where author=" + u , Post.class).list();
		return plist;
	}

	public void update(Post p) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		ses.update(p);
		tx.commit();
	}

	public void delete(Post p) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		ses.delete(p);
		tx.commit();
	}

}
