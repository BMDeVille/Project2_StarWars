/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package com.p2.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.p2.models.Post;
import com.p2.models.User;


@Repository("PostDAO")
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
		System.out.println("in get all post dao");
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

	public List<Post> selectByPid(int pid) {
		return sesFact.getCurrentSession().createQuery("from Post where pid=" + pid, Post.class).list();
	}
	
	public List<Integer> selectLikesByPid(int pid){
		List<Object> likeobs = sesFact.getCurrentSession().createNativeQuery("SELECT LIKES_ID FROM POSTS_USERS WHERE POST_PID =" + pid).list();
		System.out.println("length obs " + likeobs.size());
		List<Integer> likesid = new ArrayList<Integer>();
		for(Object obj : likeobs) {
		     Integer likeid = Integer.valueOf(obj.toString());
		     likesid.add(likeid);
		}
		return likesid;
	}

}
