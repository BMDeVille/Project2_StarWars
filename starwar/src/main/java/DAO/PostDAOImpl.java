/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import models.Post;
import models.User;


@Repository("PostDao")
@Transactional
public class PostDAOImpl implements PostDAO {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Autowired
	private SessionFactory sesFact;
	
	public PostDAOImpl() {

	}
	
	public void insert(Post p) {
		sesFact.getCurrentSession().save(p);
	}

	public List<Post> selectAll() {
		return sesFact.getCurrentSession().createQuery("from Post", Post.class).list();
	}

	public List<Post> selectByUser(User u) {
		return sesFact.getCurrentSession().createQuery("from Post where author="+u, Post.class).list();
	}

	public void update(Post p) {
		sesFact.getCurrentSession().update(p);
	}

	public void delete(Post p) {
		sesFact.getCurrentSession().delete(p);
	}

}
