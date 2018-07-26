package com.p2.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	//@PostMapping(value="/login.app")
	@RequestMapping(value = "/login.app", method = RequestMethod.POST)
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
				req.getSession().setAttribute("cur_user", u1);
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
	
	
	
}
