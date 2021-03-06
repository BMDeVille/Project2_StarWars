package com.p2.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p2.dao.DaoService;
import com.p2.models.User;

@Controller("ResetPasswordController")
//@CrossOrigin(origins="http://localhost:4200")
public class ResetPasswordController {
	final static Logger logger = Logger.getLogger(ResetPasswordController.class);

	@Autowired
	private  DaoService ds;

	public ResetPasswordController() {
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value = "/email.app")
	public void forgotPassword(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		res.setContentType("application/json");
		System.out.println("in email cont");
		String toEmail = req.getParameter("email");
		User u = ds.selectByEmail(toEmail);
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		if(u != null) {
			EmailSender.sendEmail(toEmail, u.getFname(), u.getLname(), u.getUsername(), 
					u.getAllegiance().getAid(), "http://localhost:4200/reset/", "/", "Lost Password");
			res.getWriter().write(new ObjectMapper().writeValueAsString("success"));
		} else {
			logger.info("User does not exist");
			res.getWriter().write(new ObjectMapper().writeValueAsString("failed"));
		}
	}

	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value = "/reset.app")
	public void reset(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		
		res.setContentType("application/json");

		String username = req.getParameter("username");
		User u1 = ds.selectByUsername(username);
		String sec_ans = req.getParameter("sec_ans");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		if(u1 != null) {
			if(u1.getSecurityAnswer().equals(sec_ans)) {
				// get the hash of new password
				String newPass = BCrypt.hashpw(req.getParameter("newPass"), BCrypt.gensalt());
				u1.setPassword(newPass);
				ds.updateUser(u1);
				res.getWriter().write(new ObjectMapper().writeValueAsString("success"));
				logger.info(u1.getUsername() + " changed password.");
			} else {
				logger.info("User does not exist");
				res.getWriter().write(new ObjectMapper().writeValueAsString("failed"));
				// return;
			}	
		}

	}
}
