package com.p2.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p2.dao.DaoService;
import com.p2.models.Allegiance;
import com.p2.models.User;

@Controller("ChangeUserInformation")
public class ChangeUserInformation {
	final static Logger logger = Logger.getLogger(RegisterController.class);

	
	public ChangeUserInformation() {
	}
	
	@Autowired
	public DaoService ds;
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value = "/updateAccount.app")
	public  @ResponseBody User updateAccount(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException, ParseException {
		System.out.println("You are updating a user");
		res.setContentType("application/json");

		String username = req.getParameter("username");
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		String email = req.getParameter("email");
		// DOB Section
		System.out.println(username + " " + firstName + " " + lastName + " " +email);
		System.out.println("check5");
		//date = dob
			// ------------------------------------
		String about = req.getParameter("about");

		/// creating the user object with the no id, about, and image constructor
		//User user1 = new User(username, firstName, lastName, email, password, dob1, securityAnswer, al);
		
		User user = ds.selectByEmail(email);
		user.setUsername(username);
		user.setFname(firstName);
		user.setLname(lastName);
		user.setAbout(about);		
		logger.info("user register: " + user.getUsername() + " " + user.getFname() + " updated their information.");

		System.out.println(user);

		ds.updateUser(user);
		
		System.out.println(user);

		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		//res.getWriter().write(new ObjectMapper().writeValueAsString(user));

		
		return user;

}
	
}
