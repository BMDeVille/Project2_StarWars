package service;

import java.util.List;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import models.User;

public class UserServiceImpl implements UserService{
	
	UserDAO udao = new UserDAOImpl();

	public void insert(User u) {
		
	}

	public List<User> selectAll() {

		return udao.selectAll();
	}

	public List<User> selectByUsername(String username) {
		return udao.selectByUsername(username);
	}

	public List<User> selectByFirstName(String fname) {
		return udao.selectByFirstName(fname);
	}

	public List<User> selectByLastName(String lname) {
		return udao.selectByLastName(lname);
	}

	public void update(User u) {
		
	}

	public void delete(User u) {
		
	}

}
