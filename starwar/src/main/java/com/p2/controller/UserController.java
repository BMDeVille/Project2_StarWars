package com.p2.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.p2.dao.DaoService;
import com.p2.models.User;

@Controller("UserController")
public class UserController {
	public UserController() {
	}
	
	@Autowired
	public DaoService ds;
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value="/get-by-email.app")
	public @ResponseBody User getByEmail(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		String email = req.getParameter("email");
		res.setContentType("application/json");
		return ds.selectByEmail(email);
	}
}
