package service;

import java.util.List;

import models.User;

public interface UserService {

	
	//create
		public void insert(User u);
		//read
		public List<User> selectAll();
		public List<User> selectByUsername(String username);
		public List<User> selectByFirstName(String fname);
		public List<User> selectByLastName(String lname);
		//update
		public void update(User u);
		//delete
		public void delete(User u);
}
