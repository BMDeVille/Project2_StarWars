package com.p2.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p2.dao.DaoService;
import com.p2.dao.DaoServiceImpl;
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
		res.setContentType("application/json");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		
		User u1 = ds.selectByUsername(username);
		System.out.println(u1);
		res.setHeader("Access-Control-Allow-Credentials", "true");
		if(u1 !=null) {
			//check if password match to database 
			if(BCrypt.checkpw(password, u1.getPassword())) {
				System.out.println("matched");
				logger.info("user: " + u1.getUsername() + " login ");
				
				res.getWriter().write(new ObjectMapper().writeValueAsString(u1));
				return u1;
			}	
		}
		else {
			System.out.println("no existing username");
			logger.info("no existing username: " + username);
			res.getWriter().write(new ObjectMapper().writeValueAsString("failed"));
			//return;
		}
		return u1;

	}
	
	
	
}
