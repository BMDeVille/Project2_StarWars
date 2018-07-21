/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package com.p2.dao;

import java.util.List;

import com.p2.models.Post;
import com.p2.models.User;


public interface PostDAO {
	//create
	public void insert(Post p);
	//read
	public List<Post> selectAll();
	public List<Post> selectByUser(User u);
	//update
	public void update(Post p);
	//delete
	public void delete(Post p);
}
