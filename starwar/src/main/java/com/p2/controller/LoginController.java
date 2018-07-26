package com.p2.controller;

import java.io.IOException;

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
import com.p2.models.User;


@Controller("LoginController")
//@CrossOrigin(origins="http://localhost:4200")
public class LoginController {
	public LoginController() {
	}
	
	final static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private  DaoService ds;
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value="/login.app")
	public @ResponseBody User login(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		System.out.println("in login cont");
		//manually set response type to json
		//for some reason annotation @ResponseBody won't work
		res.setContentType("application/json");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		
		User u1 = ds.selectByUsername(username);
		System.out.println("User grabbed: " + u1);
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		if(u1 !=null) {
			//check if password match to database 
			String pw = BCrypt.hashpw(password, BCrypt.gensalt());
			if(BCrypt.checkpw(password, u1.getPassword())) {
				System.out.println("matched");
				logger.info("user: " + u1.getUsername() + " login ");
				
				res.getWriter().write(new ObjectMapper().writeValueAsString(u1));
				req.getSession().setAttribute("loggedUsername", u1.getUsername());
				req.getSession().setAttribute("loggedPassword", u1.getPassword());
				return u1;
			}	
		}
		else {
			System.out.println("no existing username");
			logger.info("no existing username: " + username);
			//res.getWriter().write(new ObjectMapper().writeValueAsString("failed"));
			res.getWriter().write(new ObjectMapper().writeValueAsString(new User()));
			
			//return;
		}
		//if not found, return empty user
		return new User();

	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value="/logged.app")
	public @ResponseBody User logged(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		res.setContentType("application/json");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		
		String username = (String) req.getSession().getAttribute("loggedUsername");
		String password = (String) req.getSession().getAttribute("loggedPassword");
		if(username != null && password != null) {
			User u1 = ds.selectByUsername(username);
			if(u1 != null) {
				if(BCrypt.checkpw(password, u1.getPassword())){
					res.getWriter().write(new ObjectMapper().writeValueAsString(u1));
					return u1;
				}
			}
		}
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(new User()));
		return new User();
	}
	
	
	
}
