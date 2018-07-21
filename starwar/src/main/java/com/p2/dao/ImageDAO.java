/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package com.p2.dao;

import java.util.List;

import com.p2.models.Image;
import com.p2.models.Post;
import com.p2.models.User;

public interface ImageDAO {
	//create
	public void insert(Image img);
	//read
	public List<Image> selectAll();
	public List<Image> selectByUser(User u);
	public List<Image> selectByPost(Post p);
	//update
	public void update(Image img);
	//delete
	public void delete(Image img);
}
