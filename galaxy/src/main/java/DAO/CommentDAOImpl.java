/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import models.Comment;
import models.Post;
import util.HibernateUtil;

public class CommentDAOImpl implements CommentDAO{

	public void insert(Comment c) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		ses.save(c);
		tx.commit();
	}

	public List<Comment> selectAll() {
		Session ses = HibernateUtil.getSession();
		List<Comment> clist = ses.createQuery("from Comment", Comment.class).list();
		return clist;
	}

	public List<Comment> selectByPost(Post p) {
		Session ses = HibernateUtil.getSession();
		List<Comment> clist = ses.createQuery("from Post where parent=" + p, Comment.class).list();
		return clist;
	}

	public void update(Comment c) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		ses.update(c);
		tx.commit();
	}

	public void delete(Comment c) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		ses.delete(c);
		tx.commit();
	}

}
