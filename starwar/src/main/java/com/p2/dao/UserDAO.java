/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package com.p2.dao;

import java.util.List;

import com.p2.models.User;

public interface UserDAO {
	//create
	public void insert(User u);
	//read
	public List<User> selectAll();
	public User selectByUsername(String username);
	public List<User> selectByFirstName(String fname);
	public List<User> selectByLastName(String lname);
	public User selectByEmail(String email);
	//update
	public void update(User u);
	//delete
	public void delete(User u);
}
