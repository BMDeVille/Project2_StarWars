/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package DAO;

import java.util.List;

import models.User;

public interface UserDAO {
	//create
	public void insert(User u);
	//read
	public List<User> selectAll();
	public User selectByUsername(String username);
	public List<User> selectByFirstName(String fname);
	public List<User> selectByLastName(String lname);
	//update
	public void update(User u);
	//delete
	public void delete(User u);
}
