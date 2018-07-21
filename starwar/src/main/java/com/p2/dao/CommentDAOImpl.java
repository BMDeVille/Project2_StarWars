/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package com.p2.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.p2.models.Comment;
import com.p2.models.Post;

@Repository("CommentDAO")
@Transactional
public class CommentDAOImpl implements CommentDAO{
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Autowired
	private SessionFactory sesFact;
	
	public CommentDAOImpl() {

	}
	
	public void insert(Comment c) {
		sesFact.getCurrentSession().save(c);
	}

	public List<Comment> selectAll() {
		return sesFact.getCurrentSession().createQuery("from Comment", Comment.class).list();
	}

	public List<Comment> selectByPost(Post p) {
		return sesFact.getCurrentSession().createQuery("from Post where parent=" + p, Comment.class).list();
	}

	public void update(Comment c) {
		sesFact.getCurrentSession().update(c);
	}

	public void delete(Comment c) {
		sesFact.getCurrentSession().delete(c);
	}

}
